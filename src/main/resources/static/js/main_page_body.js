function getPostingsTable() {
    $.ajax({
        type: "GET",
        url: '/api/posting',
        dataType: 'json',
        async: true,
        success: function (result) {
            let array = result.data
            for(let step = 0; step < array.length; step++) {

                let postingDTO = array[step]
                let date = postingDTO.datePosting.substring(8,10) + "-" +
                    postingDTO.datePosting.substring(5,7) + "-" +
                    postingDTO.datePosting.substring(0,4) + " " +
                    postingDTO.datePosting.substring(11,16);
                document.getElementById('mainPageBody').innerHTML +=
                    `<div id="main_page_posting" class="col-md-3">
                        <div id="cardPosting" class="card">
                            <div id="ImageSlider${step}" class="carousel slide">
                                <ol class="carousel-indicators">
                                    <li style="border: 1px solid black;" data-target="#ImageSlider${step}" data-slide-to="0" class="active"></li>
                                    <li style="border: 1px solid black;" data-target="#ImageSlider${step}" data-slide-to="1"></li>
                                    <li style="border: 1px solid black;" data-target="#ImageSlider${step}" data-slide-to="2"></li>
                                </ol>
                                <div id="carouselInner" class="carousel-inner">
                                    <div class="carousel-item active">
                                        <a href="#">
                                            <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a href="#">
                                            <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
                                        </a>
                                    </div>
                                    <div class="carousel-item">
                                        <a>
                                            <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
                                        </a>
                                    </div>
                                </div>
                                <a class="carousel-control-prev" href="#ImageSlider${step}" role="button"
                                   data-slide="prev">
                                    <span style="background-color: gray;" class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#ImageSlider${step}" role="button"
                                   data-slide="next">
                                    <span style="background-color: gray;" class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
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