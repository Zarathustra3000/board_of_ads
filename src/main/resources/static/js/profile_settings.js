$('#profileSettings, #profile-settings-from-header').on('click', async function() {
    document.getElementById('nav-settings').className = "tab-pane fade active show";
    let userResponse = await userService.getUserInfo();
    let user = userResponse.json();
    user.then(
        principal => {
            $('#userEmail').text(principal.data.principal.email);
            $('#userId').text(principal.data.principal.id);
            $('#postCount').text(principal.data.principal.postings != null ? principal.data.principal.postings.length : 0);
            $('#fld_name').val(principal.data.principal.firsName);
            console.log(principal.data.principal.city.name);
            $('#selectedCity').val(principal.data.principal.city.name);
            $('#selectedCity').text(principal.data.principal.city.name);
        }
    );
})

$('#selectCity').on('change', function() {
    console.log('bla');
    $('#chooseCityModal').modal('show');
})

$('#tooltip').tooltip();

$('#tooltip').on('click', function() {
    $('[data-toggle="popover"]').popover()
    }
);

$('#editEmail').on('click', function() {
    $('#emailEditModal').modal('show');
    $('#editEmailButton').attr('disabled','disabled');
})

$('#editEmailButton').on('click', function() {
    $('#emailEditModal').modal('hide');
    $('#emailEditConfirmModal').modal('show');
})

$('#emailInput').on('keyup', function() {
    const email = $("#emailInput").val();
    if (validateEmail(email)) {
        $('#editEmailButton').removeAttr('disabled');
    }
})

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

$('#editConfirmEmailButton').on('click', function() {
    let data = {
        email: $("#emailInput").val(),
        password: $("#passwordInput").val()
    };
    userService.changeEmail(data).then(userResponse => {
        if (userResponse.status === 200) {
            $('#emailEditConfirmModal').modal('hide');
        }
    });
})

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
    getUserInfo: async () => {
        return await http.fetch('/api/user/', {
            method: 'GET'
        });
    },
    changeEmail: async (data) => {
        return await http.fetch('/api/user/email', {
            body: JSON.stringify(data),
            method: 'PUT'
        });
    },
    changePassword: async (data) => {
        return await http.fetch('/api/user/pswd', {
            body: JSON.stringify(data),
            method: 'PUT'
        });
    }
 }