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
                        <div id="cardPosting" class="card">
                                <a href=""><img id="postingImageRef" class="card-img-top" src="../images/empty_image.jpg" alt=""></a>
<!--                            <div id="ImageSlider" class="carousel slide" data-ride="carousel">-->
<!--                                <ol class="carousel-indicators">-->
<!--                                    <li data-target="#ImageSlider" data-slide-to="0" class="active"></li>-->
<!--                                    <li data-target="#ImageSlider" data-slide-to="1"></li>-->
<!--                                    <li data-target="#ImageSlider" data-slide-to="2"></li>-->
<!--                                </ol>-->
<!--                                <div id="carouselInner" class="carousel-inner">-->
<!--                                    <div class="carousel-item active">-->
<!--                                        <a href="#">-->
<!--                                            <img id="postingImageRef" src="../images/logo.jpg" class="card-img-top" alt="">-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                    <div class="carousel-item">-->
<!--                                        <a href="#">-->
<!--                                            <img id="postingImageRef" src="../images/logo.jpg" class="card-img-top" alt="">-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                    <div class="carousel-item">-->
<!--                                        <a>-->
<!--                                            <img id="postingImageRef" src="../images/logo.jpg" class="card-img-top" alt="">-->
<!--                                        </a>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                                <a class="carousel-control-prev" href="#ImageSlider" role="button"-->
<!--                                   data-slide="prev">-->
<!--                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<!--                                    <span class="sr-only">Previous</span>-->
<!--                                </a>-->
<!--                                <a class="carousel-control-next" href="#ImageSlider" role="button"-->
<!--                                   data-slide="next">-->
<!--                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<!--                                    <span class="sr-only">Next</span>-->
<!--                                </a>-->
<!--                            </div>-->
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