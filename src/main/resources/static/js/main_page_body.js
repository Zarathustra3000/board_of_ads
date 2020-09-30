function getPostingsTable() {
    $.ajax({
        type: "GET",
        url: '/api/posting/list',
        dataType: 'json',
        async: true,
        success: function (data) {
            // for (let step = 0; step < data.length; step++) {
            //     console.log(data[step])
            // }
            console.log(data)
        }
    })
    // document.getElementById('mainPageBody').innerHTML +=
    //     `<div class="col-md-3">
    //         <div class="card">
    //             <img class="card-img-top" src="..." alt="Card image cap">
    //                 <div class="card-body">
    //                     <a href="#">Название карточки</a>
    //                     <strong>
    //                         <div id="price">Цена</div>
    //                     </strong>
    //                     <div class="card-text text-muted">
    //                         <div id="meetingPlace">Москва, Преображенская площадь</div>
    //                         <div id="timeOfPosint">25 сентября 16:10</div>
    //                     </div>
    //                 </div>
    //         </div>
    //     </div>`

}
getPostingsTable()