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
                    `<div id="main_page_posting" class="col-md-3">
                        <div class="card">
                            <a  href="#">
                            <img id="postingImageRef" class="card-img-top" src="../images/logo.jpg" alt="">
                            </a>
                            <div id="postingCardBody" class="card-body">
                                <a id="postingTitle" class="text-primary" href="#">${postingDTO.title}</a>
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