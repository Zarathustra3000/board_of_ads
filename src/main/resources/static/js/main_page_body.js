let selectedCategoryOption = "Любая категория";
let allPostings;

function getPostingsTable(categorySelect, posts) {
    for (let step = 0; step < posts.length; step++) {
        let categorySelectTemp = categorySelect;
        let postingDTO = posts[step];

        let date = postingDTO.datePosting.substring(8, 10) + "-" +
            postingDTO.datePosting.substring(5, 7) + "-" +
            postingDTO.datePosting.substring(0, 4) + " " +
            postingDTO.datePosting.substring(11, 16);

        if (categorySelectTemp === "Любая категория") {
            categorySelectTemp = postingDTO.category;
        }

        if (postingDTO.category === categorySelectTemp) {
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
                                    <div id="meetingPlace">${postingDTO.meetingAddress}</div>
                                    <div id="timeOfPosting">${date}</div>
                                </div>
                            </div>
                        </div>
                    </div>`

            if (postingDTO.images.length > 0) {
                for (let i = 0; i < postingDTO.images.length; i++) {
                    let indicator = step + "indicator" + i;
                    if (i === 0) {
                        document.getElementById("carouselIndicators" + step).innerHTML +=
                            `<li id="${indicator}" data-target="#ImageSlider${step}" data-slide-to="i" class="active"></li>`

                        document.getElementById("carouselInner" + step).innerHTML +=
                            `<div class="carousel-item active">
                                    <a href="#">
                                        <img id="postingImageRef" src="${postingDTO.images[i].pathURL}" class="card-img-top" alt="">
                                    </a>
                                </div>`
                        $("#" + indicator).on("mouseover", function () {
                            $("#" + indicator).click();
                        });
                    } else {
                        document.getElementById("carouselIndicators" + step).innerHTML +=
                            `<li id="${indicator}" data-target="#ImageSlider${step}" data-slide-to="i"></li>`

                        document.getElementById("carouselInner" + step).innerHTML +=
                            `<div class="carousel-item">
                                    <a href="#">
                                        <img id="postingImageRef" src="${postingDTO.images[i].pathURL}" class="card-img-top" alt="">
                                    </a>
                                </div>`
                        $("#" + indicator).on("mouseover", function () {
                            $("#" + indicator).click();
                        });
                    }
                }
            } else {
                document.getElementById("carouselInner" + step).innerHTML +=
                    `<div class="carousel-item active">
                                    <a href="#">
                                        <img id="postingImageRef" src="../images/empty_image.jpg" class="card-img-top" alt="">
                                    </a>
                                </div>`
            }
        }
    }
}

async function getList() {
    let response = await userService.findAllPostings()
    allPostings = (await response.json()).data;
    getPostingsTable(selectedCategoryOption, allPostings)
}

$(document).ready(function() {
    getList();
});

function getCategoryOption(categoryOption) {
    if($("#category-select-city option:selected").val() != null) {
        allPostings = regionPosts;
    }
    for (let i = 0; i < categoryOption.options.length; i++) {
        if (categoryOption.options[i].selected) {
            selectedCategoryOption = (categoryOption.options[i].text);
        }
    }
    reinstallTable(selectedCategoryOption,allPostings);
}
function searchPosts() {
    let searchPosts =[];
    if($("#category-select-city option:selected").val() != null) {
        allPostings = regionPosts;
    }
    let textInput = $("#search-main-text").val();
    let photoOption = $("#image-select option:selected").text()
    for (let step = 0; step < allPostings.length; step++) {
        if(allPostings[step].title.toLowerCase().match(textInput.toLowerCase())) {
            if(photoOption === "С фото") {
                if(allPostings[step].images.length > 0) {
                    searchPosts.push(allPostings[step]);
                }
            } else if(photoOption === "Без фото") {
                if(allPostings[step].images.length === 0
                    || allPostings[step].images.length === null) {
                    searchPosts.push(allPostings[step]);
                }
            } else if(photoOption === "Все объявления") {
                searchPosts.push(allPostings[step]);
            }
        }
    }
    reinstallTable(selectedCategoryOption,searchPosts)
}

function reinstallTable(categoryOption,citiesArrayOption) {
    document.querySelectorAll('#main_page_posting').forEach(block => block.remove())

    getPostingsTable(categoryOption,citiesArrayOption);
}



