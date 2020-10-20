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
            $('#selectedCity').text(principal.data.principal.city != null ? principal.data.principal.city.name : '');
            $('#selectedCity').val(principal.data.principal.city != null ? principal.data.principal.city.id : 0);
            let phoneStr;
            if (principal.data.principal.phone != null) {
                phoneStr = `
                    <td class="">
                        <span class="js-phone-number">${principal.data.principal.phone}</span>
                        <i class="text-primary fa fa-pencil"></i>
                        <i class="text-danger fa fa-times" aria-hidden="true"></i>
                    </td>
                    <td class="">
                        <i class="text-success fa fa-check ml-5" aria-hidden="true"></i>
                        Подтверждён
                    </td>
                    <td class="">
                        <span class="">
                            <span class="text-muted">${principal.data.principal.postings != null ? principal.data.principal.postings.length : 0} объявлений</span>
                        </span>
                    </td>`;
            } else {
                phoneStr = `
                    <td class="">
                        <span class="js-phone-number">No phone</span>
                        <i class="text-primary fa fa-pencil"></i>
                    </td>`;
            }
            $('#phoneUser').append(phoneStr);
        }
    );
})

$('#new_password').on('keyup', function() {
    if ($('#current_password').val() !== "" && $('#new_password').val() !== "") {
        $('#changePasswordButton').removeAttr('disabled');
    }
})

$('#changePasswordButton').on('click', function() {
    let data = {
        email: '',
        password: $('#current_password').val(),
        newPassword: $('#new_password').val(),
        firstName: '',
        cityId: ''
    };
    userService.changeUserData(data)
        .then(userResponse => userResponse.json())
        .then(userResponse => {
            if (userResponse.success !== true) {
                $('#errorPassMessage').append(userResponse.error.text);
            } else {
                $('#SuccessMessage').removeClass("d-none");
                $('#SuccessMessage').addClass("bg-success text-dark py-2 d-block");
                $('#SuccessMessage').text("");
                $('#SuccessMessage').append('Пароль успешно изменен');
            }
        });
})

$('#selectCity').on('change', function() {
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
        password: $("#passwordInput").val(),
        newPassword: '',
        firstName: '',
        cityId: ''
    };
    userService.changeUserData(data)
        .then(userResponse => userResponse.json())
        .then(userResponse => {
         if (userResponse.success !== true) {
             $('#errorMessage').append(userResponse.error.text);
         } else {
             $('#emailEditConfirmModal').modal('hide');
         }
    });
})

$("#saveCityOrNameButton").on('click', function() {
    let data = {
        email: '',
        password: '',
        newPassword: '',
        firstName: $("#fld_name").val(),
        cityId: $("#selectCity").val()
    };
    userService.changeUserData(data)
        .then(userResponse => userResponse.json())
        .then(userResponse => {
            if (userResponse.success === true) {
                $('#SuccessMessage').removeClass("d-none");
                $('#SuccessMessage').addClass("bg-success text-dark py-2 d-block");
                $('#SuccessMessage').text("");
                $('#SuccessMessage').append('Контактная информация успешно сохранена');
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
    changeUserData: async (data) => {
        return await http.fetch('/api/user/', {
            body: JSON.stringify(data),
            method: 'PUT'
        });
    }
 }