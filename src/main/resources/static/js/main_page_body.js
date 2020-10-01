function getPostingsTable() {
    $.ajax({
        type: "GET",
        url: '/api/posting',
        dataType: 'json',
        async: true,
        success: function (data) {
            for (let step = 0; step < data.length; step++) {
                let postingDTO = data[step]
                let date = postingDTO.datePosting.substring(8,10) + "-" +
                    postingDTO.datePosting.substring(5,7) + "-" +
                    postingDTO.datePosting.substring(0,4) + " " +
                    postingDTO.datePosting.substring(11,16);

                document.getElementById('mainPageBody').innerHTML +=
                    `<div class="col-md-3" style="height: 320px;width: 208px">
                        <div class="card">
                            <a href="#"><img class="card-img-top" src="../images/logo.jpg" alt=""
                            style="width: 100%;height: 200px; display: block"></a>
                                <div class="card-body">
                                    <a href="#">${postingDTO.title}</a>
                                    <strong>
                                        <div id="price">${postingDTO.price} ₽</div>
                                    </strong>
                                    <div class="card-text text-muted">
                                        <div id="meetingPlace">Москва, Преображенская площадь</div>
                                        <div id="timeOfPosting">${date}</div>
                                    </div>
                                </div>
                        </div>
                    </div>`
            }
        }
    })
}
getPostingsTable()