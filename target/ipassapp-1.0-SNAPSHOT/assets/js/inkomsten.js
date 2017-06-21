$(document).ready(initPage());
function initPage() {
    loadTotalInkomsten();
}

function loadTotalInkomsten() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/inkomst/getsum/" + user_id, function (data) {
        console.log(data);
        $("#totalInkomst").empty();
        $("#totalInkomst").append(data['totaalInkomst']);
    });
}