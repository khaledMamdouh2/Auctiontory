function socketListener(message, channel, event) {
    var c = "";
    auctions = JSON.parse(message);
    if (!Array.isArray(auctions)) {
        var auction = auctions;
        var auctionDate = new Date(auction.deadline);
        var formattedDate = auctionDate.getDate() + "/" + eval(auctionDate.getMonth() + 1) + "/" + auctionDate.getFullYear() + " " + auctionDate.getHours() + ":" + auctionDate.getMinutes();
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
<label class="label label-fill label-success">Active Now</label>
                                                                    <hr/>
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