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
                            <div id="ImageSlider${step}" class="carousel slide" data-interval="false">
                                <ol id="carouselIndicators${step}" class="carousel-indicators">
                                    
                                </ol>
                                <div id="carouselInner${step}" class="carousel-inner">
                                    
                                </div>
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

                if(postingDTO.images.length > 0) {
                    for(let i = 0; i < postingDTO.images.length; i++) {
                        if(i === 0) {
                            document.getElementById("carouselIndicators"+step).innerHTML +=
                                `<li data-target="#ImageSlider${step}" data-slide-to="i" class="active"></li>`

                            document.getElementById("carouselInner"+step).innerHTML +=
                                `<div class="carousel-item active">
                                    <a href="#">
                                        <img id="postingImageRef" src="${postingDTO.images[i].pathURL}" class="card-img-top" alt="">
                                    </a>
                                </div>`
                        } else {
                            document.getElementById("carouselIndicators" + step).innerHTML +=
                                `<li data-target="#ImageSlider${step}" data-slide-to="i"></li>`

                            document.getElementById("carouselInner" + step).innerHTML +=
                                `<div class="carousel-item">
                                    <a href="#">
                                        <img id="postingImageRef" src="${postingDTO.images[i].pathURL}" class="card-img-top" alt="">
                                    </a>
                                </div>`
                        }

                    }
                } else {
                    document.getElementById("carouselInner"+step).innerHTML +=
                        `<div class="carousel-item active">
                                    <a href="#">
                                        <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
                                    </a>
                                </div>`
                }
            }
        }
    })
}
getPostingsTable()

// <li data-target="#ImageSlider${step}" data-slide-to="0" class="active"></li>
// <li data-target="#ImageSlider${step}" data-slide-to="1"></li>
// <li data-target="#ImageSlider${step}" data-slide-to="2"></li>

// <div class="carousel-item active">
//     <a href="#">
//     <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
//     </a>
// </div>
// <div class="carousel-item">
// <a href="#">
// <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
// </a>
// </div>
// <div class="carousel-item">
// <a>
// <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
// </a>
// </div>