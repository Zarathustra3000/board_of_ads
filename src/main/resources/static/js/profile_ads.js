let usersProfilePostings = $('#userPo tbody');
let usersProfileArchivePostings = $('#userPoArch tbody');

$(document).ready(function () {
 getAllUserPosts();
});

function getAllUserPosts() {
    let user_id = document.getElementById('userpostid').textContent;
    profileService.findPostingByUserId(user_id).then((response => {

        let activeCounter = 0;
        let archiveCounter = 0;
        let soldAmountCounter = 0;

        response.data.map(post => {

            let postingImage = document.createElement('img');
            postingImage.setAttribute('id', 'postingImg');
            postingImage.setAttribute('src', '../images/empty_image.jpg');
            postingImage.setAttribute('width', '210');
            postingImage.setAttribute('height', '150');

            for (let o in post) {
                let li = document.createElement('li');
                let tr = document.createElement('tr');
                let trPrice = document.createElement('tr');
                let trMeeting = document.createElement('tr');
                let td = document.createElement('td');
                let tdTitle = document.createElement('td');
                let tdPrice = document.createElement('td');
                let tdMeeting = document.createElement('td');
                let tdTitleHref = document.createElement('a');

                let title = document.createTextNode(post.title);
                let price = document.createTextNode(post.price + ' ₽');
                let meeting = document.createTextNode(post.meetingAddress);

                li.setAttribute('class', 'dropdown-divider');
                li.setAttribute('width', '500');
                tr.setAttribute('id', "userPosting");
                td.setAttribute('rowspan', "3");
                td.setAttribute('class', "first");
                td.setAttribute('width','210');
                tdTitleHref.setAttribute('class', 'text-primary');
                tdTitleHref.setAttribute('href', '#');
                tdTitleHref.appendChild(title);

                tdTitle.appendChild(tdTitleHref);
                tdPrice.appendChild(price);
                tdMeeting.appendChild(meeting);
                td.appendChild(postingImage);
                tr.appendChild(td);
                tr.appendChild(tdTitle);
                trPrice.appendChild(tdPrice);
                trMeeting.appendChild(tdMeeting);

                if (post.isActive) {
                    activeCounter = activeCounter + 1;
                    usersProfilePostings.append(tr).append(trPrice).append(trMeeting).append(li);
                } else {
                    archiveCounter = archiveCounter + 1;
                    soldAmountCounter = post.price + soldAmountCounter;
                    console.log(soldAmountCounter);
                    usersProfileArchivePostings.append(tr).append(trPrice).append(trMeeting).append(li);
                }
                break;
            }
        });
        document.getElementById('active-ads-tab').innerText = 'Активные ' + ' - ' + activeCounter;
        document.getElementById('archive-ads-tab').innerText = 'Архивные ' + ' - ' + archiveCounter;
document.getElementById('amountOfSales').innerHTML = soldAmountCounter + ' ₽';
    }));
}

const httpHeader = {
    fetch: async function (url, options = {}) {
        const response = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });
        return response.json();
    }
};

const profileService = {
    findPostingByUserId: async (id) => {
        return await httpHeader.fetch('api/posting/userpost/' + id);
    }
};