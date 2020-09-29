let buttonAdd = $('#searchCityDiv');

$("#region, #category-select-city").click(function() {
    $('#searchModel').modal('show');
});

$('select#cities').on('change', function() {
    $('input[name="cityInput"]').val(this.value);
});

function onOptionHover() {
    $(".opt").mouseover(
        function() {
            $(this).css('background', '#99ccff')
        });
    $(".opt").mouseleave(
        function() {
            $(this).css('background', '#fff')
        });
}

function onClickOpt(id) {
    document.getElementById('cityInput').value = "";
    $('#category-select-city').empty();
    $('#citiesSelect').empty();
    $('#searchModel').modal('hide');
    let row = `<option>` + id + `</option>`;
    $('#category-select-city').append(row);
    document.getElementById('category-select-city').disabled = false;
}

$(document).ready(function() {
    viewCities();
});

let cities;

async function viewCities() {
    $('#category-select-city').empty();
    const usersResponse = await userService.findAllCity();
    cities = usersResponse.json();
    let button = `<div style="position: absolute; top: 350px; left: 700px">
                    <button 
                        type="button" 
                        class="btn btn-primary "                           
                        disabled>Показать 0 объявлений
                       </button>
                </div>`;
    buttonAdd.append(button);
}

$('.typeahead').on('keyup', function() {
    addOptions();
});

function addOptions() {
    $('#citiesSelect').remove();
    $('#citiesSelect').empty();
    let select=`<select id="citiesSelect" size="7"
                                class="form-control">
                        </select>`;
    $('.citiesOptions').append(select);
    let addForm = $(".typeahead").val().toLowerCase();
    cities.then(cities => {
        cities.forEach(city => {
            if (city.name.toLowerCase().includes(addForm)) {
                let userRow = `<option onmouseover="onOptionHover()" 
                                       onclick="onClickOpt(this.id)"
                                       id="${city.name}"
                                       class="opt"                                
                                       text="${city.name}">
                                           <div>${city.name}</div>
                                           <div>${' ' + city.regionFormSubject}</div>
                                </option>`;
                $('#citiesSelect').append(userRow);
            }
        });
    });
}

const http = {
    fetch: async function(url, options = {}) {
        const response = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });
        return response;
    }
};

const userService = {
    findAllCity: async () => {
        return await http.fetch('/api/city');
    }
}

$.get("/user", function(data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});