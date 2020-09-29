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

async function onClickOpt(id) {
    console.log(id);
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
        posts.forEach(() => {
            sizeArray++;
        })
    }).then(() => {
            let button = `<div >
                    <button 
                        type="button" 
                        class="btn btn-primary"   
                        id="countPostButton"
                        style="position: absolute; top: 350px; left: 700px"                       
                        >Показать ` + sizeArray + ` объявлений
                       </button>
                </div>`;
            buttonAdd.append(button);
        }
    );
}

$(document).ready(function() {
    viewCities();
});

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
        posts.forEach(() => {
            sizeArray++;
        })
    }).then(() => {
            let button = `<div >
                    <button 
                        type="button" 
                        class="btn btn-primary"   
                        id="countPostButton"
                        style="position: absolute; top: 350px; left: 700px"                       
                        >Показать ` + sizeArray + ` объявлений
                       </button>
                </div>`;
            buttonAdd.append(button);
        }
    );
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

$.get("/user", function(data) {
    $("#user").html(data.userAuthentication.details.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
});