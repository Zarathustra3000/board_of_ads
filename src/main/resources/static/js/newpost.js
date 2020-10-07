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
            let array = result.data;

            for (let i = 0; i < array.length; i++) {
                let x = array[i];
                if (x.parentTitle == categoryName) {
                    document.getElementById("cascade-table-2").innerHTML +=
                        `<div class="category-table-button unactive-category-table-button" id="category-table-button" 
                            onclick="clickOnCategoryButton()" onmouseover="hoverOnCategoryButton()">
                        ${x.name}
                    </div>`
                }
            }
        }
    })
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

function clickOnCategoryButton(o, categoryName) {
    $(".category-table-button").removeClass("active-category-table-button")
        .addClass("unactive-category-table-button").css("background-color", "#fff");
    $(o).removeClass("unactive-category-table-button")
        .addClass("active-category-table-button").css("background-color", "#0af", "color", "#fff");
    console.log(categoryName);
};


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
            console.log("qwe")
        });
}


