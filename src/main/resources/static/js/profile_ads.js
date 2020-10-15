let getAllUserAds = 'http://localhost:5556/api/posting/userpost/';

$(document).ready(function () {
 getAllUserPosts();
});

function getAllUserPosts() {
    let user_id = document.getElementById('userpostid').textContent;
    console.log(user_id);
}