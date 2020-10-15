






$(document).ready(function () {
    getPostingInfo($("#postingId").text().valueOf());
});

async function getPostingDto(id) {
    let response = await fetch("/api/posting/" + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    return (await response.json()).data;
}
async function getCategoryByName(name) {
    let response = await fetch("/api/category/" + name, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    return (await response.json()).data;
}

async function getPostingInfo(id) {
    let categoriesLine = $("#categoriesHrefs");

    let postingDto = await getPostingDto(id);
    let categoryDto = postingDto.category;

    let liCity = `<li><a href="#">${postingDto.city}</a></li>`;
    let liDot = `<li>·</li>`;
    let li;

    categoriesLine.append(liCity);
    categoriesLine.append(liDot);

    while (true) {
        li = `<li><a href="#">${categoryDto.name}</a></li>`;
        categoriesLine.append(li);
        if (categoryDto.layer !== 1) {
            categoriesLine.append(liDot);
            categoryDto = getCategoryByName(categoryDto.parentName);
        } else {
            break;
        }
    }

    if (postingDto.images.length > 0) {

        for (let i = 0; i < postingDto.images.length; i++) {
            let indicator = "indicator" + i;
            if (i === 0) {

                document.getElementById('carousel-indic').innerHTML +=
                    `<li id="${indicator}" data-target="#ImageSlider" data-slide-to="i" class="active">
                        <img class="img-fluid" src="${postingDto.images[i].pathURL}" alt="">
                    </li>`

                document.getElementById('carouselInner').innerHTML +=
                    `<div class="carousel-item active">
                        <img class="d-block w-100" style="height:500px" src="${postingDto.images[i].pathURL}" alt="">
                    </div>`

                $("#" + indicator).on("mouseover", function () {
                    $("#" + indicator).click();
                });

            } else {

                document.getElementById('carousel-indic').innerHTML +=
                    `<li id="${indicator}" data-target="#ImageSlider" data-slide-to="i">
                        <img class="img-fluid" src="${postingDto.images[i].pathURL}" alt="">
                    </li>`

                document.getElementById('carouselInner').innerHTML +=
                    `<div class="carousel-item">
                        <img class="d-block w-100" style="height:500px" src="${postingDto.images[i].pathURL}" alt="">
                    </div>`

                $("#" + indicator).on("mouseover", function () {
                    $("#" + indicator).click();
                });
            }
        }

        document.getElementById('carouselInner').innerHTML +=
            `<a class="carousel-control-prev" href="#ImageSlider" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#ImageSlider" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>`
    } else {
        let indicator = "indicator0";
        document.getElementById('carouselInner').innerHTML +=
            `<div class="carousel-item active">
                <img class="d-block w-100" style="height:500px" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22348%22%20height%3D%22225%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20348%20225%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_1751bef69e3%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A17pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1751bef69e3%22%3E%3Crect%20width%3D%22348%22%20height%3D%22225%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22116.71875%22%20y%3D%22120.3%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="">
            </div>`

        document.getElementById('carousel-indic').innerHTML +=
            `<li id="${indicator}" data-target="#ImageSlider" data-slide-to="0" class="active">
                        <img class="img-fluid" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22348%22%20height%3D%22225%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20348%20225%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_1751bef69e3%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A17pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1751bef69e3%22%3E%3Crect%20width%3D%22348%22%20height%3D%22225%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22116.71875%22%20y%3D%22120.3%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="">
                    </li>`

        $("#" + indicator).on("mouseover", function () {
            $("#" + indicator).click();
        });

        for( let x = 1; x < 7; x++) {
            indicator = "indicator" + x;
            document.getElementById('carouselInner').innerHTML +=
                `<div class="carousel-item">
                <img class="d-block w-100" style="height:500px" src="../images/empty_image.jpg" alt="">
            </div>`

            document.getElementById('carousel-indic').innerHTML +=
                `<li id="${indicator}" data-target="#ImageSlider" data-slide-to="${x}">
                        <img class="img-fluid" src="../images/empty_image.jpg" alt="">
                    </li>`

            $("#" + indicator).on("mouseover", function () {
                $("#" + indicator).click();
            });
        }

        document.getElementById('carouselInner').innerHTML +=
            `<a class="carousel-control-prev" href="#ImageSlider" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#ImageSlider" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>`


        // document.getElementById('carouselInner').innerHTML +=
        //     `<div class="carousel-item active">
        //         <img class="d-block w-100" style="height:500px" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22348%22%20height%3D%22225%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20348%20225%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_1751bef69e3%20text%20%7B%20fill%3A%23eceeef%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A17pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_1751bef69e3%22%3E%3Crect%20width%3D%22348%22%20height%3D%22225%22%20fill%3D%22%2355595c%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22116.71875%22%20y%3D%22120.3%22%3EThumbnail%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="">
        //     </div>`
    }
    // src="../images/empty_image.jpg"

    let date = postingDto.datePosting.substring(8, 10) + "-" +
        postingDto.datePosting.substring(5, 7) + "-" +
        postingDto.datePosting.substring(0, 4) + " " +
        postingDto.datePosting.substring(11, 16);

    $("#timeOfPosting").append(date);
    $("#price").append(`${postingDto.price} ₽`);
    $("#contactNumber").append(`<br>${postingDto.contact}`);
    $("#address").append(`${postingDto.meetingAddress}`);
    $("#description").append(`${postingDto.description}`);

}

