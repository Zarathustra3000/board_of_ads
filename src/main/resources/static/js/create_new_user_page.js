let newUserUrl = 'http://localhost:5556/api/user/modal-reg';
let elementCreateUser = document.getElementById('createUser');

elementCreateUser.onclick = function () {
  regNewUser();
};

async function regNewUser() {
    let data = {

        firsName: $('#userName').val(),
        password: $('#password').val(),
        email: $('#email').val(),

    };

    console.log(data);

    const response = await fetch(newUserUrl, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    })
        .catch(error => {
            console.log(error);
        });

    return response.json();
}