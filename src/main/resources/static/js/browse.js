$(document).ready(function(){
	$("loadingIcon").hide();
});
const openModal = a => {
	$("body").find("*").attr("disabled", "disabled");
	$("body").find("a").click(function (e) { e.preventDefault(); });
	$(".modal-body").hide();
	$("loadingIcon").show();
	let vehicle;
	$.ajax({
        type: "GET",
        async: false,
        url: "/browsecatalog/"+a,
        success: function(result) {
            vehicle=result
            $("body").find("*").removeAttr("disabled");
            $("body").find("a").unbind("click");
            $("loadingIcon").hide();
        },
		error: function(result) {
			$("body").find("*").removeAttr("disabled");
            $("body").find("a").unbind("click");
            $("loadingIcon").hide();
		}
    });
	if(vehicle){
		let licensePlateButtons = $(".licenseButtons");
		console.log(licensePlateButtons.length);
		if(licensePlateButtons.length > 1){
			for (let index = 0; index < licensePlateButtons.length; index++ ) {
				if(JSON.stringify(licensePlateButtons[index].getAttribute("id")) === JSON.stringify(a)){
					if(index == licensePlateButtons.length - 1){
						let previousId = licensePlateButtons[index-1].getAttribute("id");
						$("#modalFooterButtons").html(
								'<button type="button" class="btn btn-primary btnPrevious" onclick="openModal(\''+previousId+'\')">Previous</button>'
						)
					} else if (index == 0){
						let nextId = licensePlateButtons[index+1].getAttribute("id");
						$("#modalFooterButtons").html(
								'<button type="button" class="btn btn-primary btnNext" onclick="openModal(\''+nextId+'\')">Next</button>'
						)
					} else {
						let previousId = licensePlateButtons[index-1].getAttribute("id");
						let nextId = licensePlateButtons[index+1].getAttribute("id");
						$("#modalFooterButtons").html(
								'<button type="button" class="btn btn-primary btnPrevious" onclick="openModal(\''+previousId+'\')">Previous</button>'+
								'<button type="button" class="btn btn-primary btnNext" onclick="openModal(\''+nextId+'\')">Next</button>'
						)
					}
					break;
				}
			}
		}
		$("#modal-vehicleImage").attr({ "src": vehicle.image }); // update with Q5.jpg with '+vehicle.image+' when you put image in the model of catalog.
		$("#modal-vehicleType").html(vehicle.type);
		$("#modal-vehicleModel").html(vehicle.model);
		$("#modal-vehicleMake").html(vehicle.make);
		$("#modal-vehicleYear").html(vehicle.year);
		$("#modal-vehicleColor").html(vehicle.color);
		$("#modal-vehicleLicensePlate").html(vehicle.vehicleLicensePlate);
		$("#modal-vehicleStatus").html(vehicle.status);
		$(".modal-body").show();
	}
	if((!($("#myModal").data('bs.modal') || {}).isShown)){
		$('#myModal').modal('show');
	}
}