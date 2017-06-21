$(document).ready(initPage());
function initPage() {
    loadTotalUitgaves();
}

function loadTotalUitgaves() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/uitgave/getsum/" + user_id, function (data) {
        console.log(data);
        $("#totalUitgave").empty();
        $("#totalUitgave").append(data['totalUitgave']);
    });
}