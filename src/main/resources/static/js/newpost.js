$(document).ready(function () {
    getCategoryTable();
})

function getCategoryTable2(categoryName) {
    $.ajax({
        type: "GET",
        url: "/api/category",
        dataType: "json",
        async: true,
        success: function (result) {
            console.log(result);
            console.log(result.data);
            let array = result.data;
            $("#cascade-table-2").css("display", "block");
            document.getElementById("cascade-table-2").innerHTML =
                `<div class="category-table-title">
                    ${categoryName}
                 </div>`;
            for (let i = 0; i < array.length; i++) {
                let x = array[i];
                if (x.parentName == categoryName) {
                    document.getElementById("cascade-table-2").innerHTML +=
                        `<div class="category-table-button-2 unactive-category-table-button-2" id="category-table-button-2" 
                            onclick="clickOnCategoryButton2(this)" onmouseover="hoverOnCategoryButton2()">
                        ${x.name}
                    </div>`
                }
            }
        }
    })
}

function hoverOnCategoryButton2() {
    $(".category-table-button-2").hover(
        function () {
            if (!this.classList.contains("active-category-table-button-2")) {
                $(this).css("background-color", "#a5eaf8");
            }
        }, function () {
            if (!this.classList.contains("active-category-table-button-2")) {
                $(this).css("background-color", "#fff");
            }
        });
}

function clickOnCategoryButton2(o) {
    $(".category-table-button-2").removeClass("active-category-table-button-2")
        .addClass("unactive-category-table-button-2").css("background-color", "#fff");
    $(o).removeClass("unactive-category-table-button-2")
        .addClass("active-category-table-button-2").css("background-color", "#0af", "color", "#fff");
}

function getCategoryTable() {
    $.ajax({
        type: "GET",
        url: '/api/category',
        dataType: "json",
        async: true,
        success: function (result) {
            let array = result.data;

            for (let i = 0; i < array.length; i++) {
                let x = array[i];
                if (x.parent == true) {
                    document.getElementById("cascade-table").innerHTML +=
                        `<div class="category-table-button unactive-category-table-button" id="category-table-button" 
                            onclick="clickOnCategoryButton(this,'${x.name}')" onmouseover="hoverOnCategoryButton()">
                        ${x.name}
                    </div>`
                }
            }
        }
    })
}

function clickOnCategoryButton(o, category) {
    $(".category-table-button").removeClass("active-category-table-button")
        .addClass("unactive-category-table-button").css("background-color", "#fff");
    $(o).removeClass("unactive-category-table-button")
        .addClass("active-category-table-button").css("background-color", "#0af", "color", "#fff");
    console.log(category)
    getCategoryTable2(category);
}

function hoverOnCategoryButton() {
    $(".category-table-button").hover(
        function () {
            if (!this.classList.contains("active-category-table-button")) {
                $(this).css("background-color", "#a5eaf8");
            }
        }, function () {
            if (!this.classList.contains("active-category-table-button")) {
                $(this).css("background-color", "#fff");
            }
        });
}


