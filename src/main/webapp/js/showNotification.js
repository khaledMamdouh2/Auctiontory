function showBidNotification (message, channel, event , id) {
	var res = message.split("-");
	var auctionName = res[0];
	console.log(auctionName);
	for(i=1; i<res.length; i++){
		if(userId == res[i]){
			document.getElementById("auctionName").innerHTML=auctionName;
			$("#notification").fadeIn();
			setTimeout(function () {
				$("#notification").fadeOut();
			},10000);
		}
	}
}