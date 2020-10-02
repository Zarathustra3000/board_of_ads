let buttonAdd = $('#searchCityDiv');

$("#region, #category-select-city").click(function () {
    $('#searchModel').modal('show');
});

let changedCityName;

function clickCountButton() {
    $('#category-select-city').empty();
    $('#cityInput').empty();
    $('#searchModel').modal('hide');
    let row = `<option>` + changedCityName + `</option>`;
    $('#category-select-city').append(row);
}

$('select#cities').on('change', function () {
    $('input[name="cityInput"]').val(this.value);
});

function onOptionHover() {
    $(".opt").mouseover(
        function () {
            $(this).css('background', '#99ccff')
        });
    $(".opt").mouseleave(
        function () {
            $(this).css('background', '#fff')
        });
}

async function onClickOpt(id) {
    changedCityName = id;
    $('.typeahead').val(id);
    $('#citiesSelect').remove();
    let usersResponse;
    if (id.includes('Область')
        || id.includes('Край')
        || id.includes('Республика')
        || id.includes('Автономный округ')
        || id.includes('Город')
    ) {
        usersResponse = await userService.findPostingByRegionName(id);
    } else {
        usersResponse = await userService.findPostingByCityName(id);
    }
    posts = usersResponse.json();
    console.log(posts);
    $('#countPostButton').empty();
    let sizeArray = 0;
    posts.then(posts => {
        posts.data.forEach(() => {
            sizeArray++;
        })
    }).then(() => {
            $('#countPostButton').remove();
            let button = `<button
                                type="button"
                                class="btn btn-primary button-count-post"
                                onclick="clickCountButton()"
                                id="countPostButton">Показать ` + sizeArray + ` объявлений
                          </button>`;
            buttonAdd.append(button);
        }
    );
}

$(document).ready(function () {
    viewCities();
    $('#buttonAuth').on('click', function () {
        authorization();
    });
});


async function authorization() {
    let user = {
        email: $("#tel-and-email").val(),
        password: $("#password").val()
    };

    try {
        const response = await fetch('http://localhost:5556/auth', {
            method: "POST",
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        if (response.status === 200) {
            $('#registrationModalCenter').modal('hide');
            $('#tel-and-email').val("");
            $('#password').val("");
            location.reload();
        } else {
            $('#tel-and-email').css('border-color', 'red');
            $('#password').css('border-color', 'red');
        }
    } catch (error) {
        console.log('Возникла проблема с вашим fetch запросом: ', error.message);
    }
}


let cities;
let posts;


async function viewCities() {
    $('#category-select-city').empty();
    const usersResponse = await userService.findAllCity();
    cities = usersResponse.json();
    const postsResponse = await userService.findAllPostings();
    posts = postsResponse.json();
    let sizeArray = 0;
    console.log(posts);
    posts.then(posts => {
        posts.data.forEach(() => {
            sizeArray++;
        })
    }).then(() => {
            let button = `<button 
                                type="button" 
                                class="btn btn-primary button-count-post"   
                                id="countPostButton">Показать ` + sizeArray + ` объявлений
                          </button>`;
            buttonAdd.append(button);
        }
    );
}

$('.typeahead').on('keyup', function () {
    addOptions();
    $('#countPostButton').attr("disabled", true);
});

function addOptions() {
    $('#citiesSelect').remove();
    $('#citiesSelect').empty();
    let select = `<select id="citiesSelect" size="7" class="form-control"></select>`;
    $('.citiesOptions').append(select);
    let addForm = $(".typeahead").val().toLowerCase();
    cities.then(cities => {
        cities.data.forEach(city => {
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
    fetch: async function (url, options = {}) {
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
    },
    findPostingByCityName: async (name) => {
        return await http.fetch('api/posting/city/' + name);
    },
    findPostingByRegionName: async (name) => {
        return await http.fetch('api/posting/region/' + name);
    },
    findAllPostings: async () => {
        return await http.fetch('api/posting/');
    }
}

$.get("/user", function (data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});