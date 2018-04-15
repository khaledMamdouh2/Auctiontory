function socketListener(message, channel, event) {
    var c = "";
    auctions = JSON.parse(message);
    if (Array.isArray(auctions)) {
        for (i = 0; i < auctions.length; i++) {
            var auction = auctions[i];
            var id = 'auctiontory' + auction.id;
            if ($("#" + id)[0] != null && $("#" + id)[0] != undefined) {
                bidsCount = $("#" + id).find(".bidsCount");
                bidsCount[0].innerHTML = 'Bids: ' + auction.numberOfBids;

                if (auction.highestBidderId == null) {
                    bidArea = $("#" + id).find(".bidArea");
                    bidArea[0].innerHTML = `<label class="label label-fill label-warning">No Bids Yet</label>`;
                }
                else {
                    bidArea = $("#" + id).find(".bidArea");
                    bidArea[0].innerHTML = ` <p class="text-muted">
                                                <span class="highestBid">Highest: $` + auction.highestBid + ` </span>
                                                <h:panelGroup
                                                        rendered="#{ (userBean.user.type != null) and (userBean.user.type eq 'ADMIN')}"
                                                        styleClass="highestBidder">by ` + auction.highestBidderId.userName + ` </h:panelGroup>
                                            </p>`;

                }

            }
        }
    }
    else {
        var auction = auctions;
        var auctionDate = new Date("Apr 24, 2018 10:55:00 AM");


        var formattedDate = auctionDate.getDate() + "/" + auctionDate.getMonth() + 1 + "/" + auctionDate.getFullYear() + " " + auctionDate.getHours() + ":" + auctionDate.getMinutes();
        var auctionHtml =
            `
                            <div class="col-sm-3" style="margin-bottom: 40px" id="auctiontory` + auction.id + `">
                                <center>
                                    <h:form>
                                        <img src="assets/img/mockup.png" alt="` + auction.title + `" class="img-rounded"
                                             style="width: 250px"/>
                                    </h:form>
                                    <div style="overflow-wrap: break-word">
                                        <h4>
                                            `
            + auction.title +
            (type != null ? (type == "ADMIN" ? "by" + auction.ownerId.userName : "") : "")
            + `
                                        </h4>
                                    </div>
                                    <h5 class="bidsCount">Bids: 0</h5>
                                    <hr/>
                                    <div class="bidArea">
                                    <label class="label label-fill label-warning">No Bids!</label>
                                    </div>
                                    <h5>
                                        Ends at: ` + formattedDate + `
                                    </h5>
                                    <button type="button" class="btn btn-info btn-block btn-fill" onclick="` +
            (type != null ? "goToAuction(" + auction.id + ")" : "")

            + `">Go to Details</button>
                                </center>
                            </div>
        `;
        $("#auctions").append(auctionHtml);
    }

}

function goToAuction(id) {
    $("#auctionId")[0].value = id;
    $("#showAuctionBtn")[0].click();
}