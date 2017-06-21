$(document).ready(
    //Initialize page
    initPage()
);
function initPage() {
    //Load tables
    loadUitgaves();
    loadInkomsten();

    //Load Money
    // loadTotalUitgaves();
    // loadRestTotaal();
    // loadTotalInkomsten();

    //Id specific actions
    $("#inkomstTable").click(itemOptions);

    $("#uitgaveTable").click(itemOptions);
    $("#save").click(updateItem);
}

//TODO : update, delete

function loadUitgaves() {
    var userid = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/get/uitgaves/"+ userid, function (data) {
        var uitgaveTable = $("#uitgaveTable");
        var uitgaveTableBody = $("#uitgaveTable tbody");
        uitgaveTableBody.empty();

        $.each(data, function (index, object) {
            var String =
                "<tr  id='" + object.uitgave_id+"'" +">" +
                object.uitgave_id +
                "'>" +
                "<td id='" + object.uitgave_id+"'" +">" +
                object.beschrijving +
                "</td>" +
                "<td id='" + object.uitgave_id+"'" +">" +
                object.bedrag +
                "</td>" +
                "<td id='" + object.uitgave_id+"'" +">" +
                object.soort_uitgave +
                "</td>" +
                "<td id='" + object.uitgave_id+"'" +">" +
                object.uitgave_datum +
                "</td>" +
                "<td><div class='ui icon buttons centered aligned '>" +
                "<button class='mini ui negative button'> " +
                "<i id='" + object.uitgave_id +"' class='ban icon'></i></button>" +
                "<div class='or'></div> " +
                "<button class='mini ui positive button'>" +
                "<i id='" + object.uitgave_id +"' class='write icon'></i></button>" +
                "</div></td>" +
                "</tr>";
            uitgaveTable.append(String);
        });
        loadTotalUitgaves();
        loadRestTotaal();
    });
}
function loadInkomsten() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/get/inkomsten/"+ user_id, function (data) {
        var inkomstTable = $("#inkomstTable");
        var inkomstTableBody = $("#inkomstTable tbody");
        inkomstTableBody.empty();
        $.each(data, function (index, object) {
            var String =
                "<tr id='" +
                object.inkomst_id +
                "'>" +
                "<td id='" + object.inkomst_id+"'" +">" +
                object.beschrijving +
                "</td>" +
                "<td id='" + object.inkomst_id+"'" +">" +
                object.bedrag +
                "</td>" +
                "<td  id='" + object.inkomst_id+"'" +">" +
                object.soort_inkomst +
                "</td>" +
                "<td id='" + object.inkomst_id+"'" +">" +
                object.inkomst_datum +
                "</td>" +
                "<td><div class='ui icon buttons centered aligned '>" +
                "<button class='mini ui negative button'> " +
                "<i id='" + object.inkomst_id +"' class='ban icon'></i></button>" +
                "<div class='or'></div> " +
                "<button class='mini ui positive button'>" +
                "<i id='" + object.inkomst_id +"' class='write icon'></i></button>" +
                "</div></td>" +
                "</tr>";
            inkomstTable.append(String);
        });
        loadTotalInkomsten();
        loadRestTotaal();
    });
}

function getItem(e) {
    var rowId = e.target.id;
    var type = e.currentTarget.id;
    var url="";
    var formInputs="";
    var modal="";
    if(type.match("uitgave")){
        url = "/restservices/dashboard/uitgave/get/";
        modal = $('.uitgaveTableModal');
        formInputs = $("#UitgaveForm input");
    } else {

        url = "/restservices/dashboard/inkomst/get/";
        modal = $('.inkomstTableModal');
        formInputs = $("#InkomstForm input");

    }
    $.get(url + rowId, function(data) {
        console.log(data);
        $.each(formInputs, function(index, input) {
            input.value = data[input.name];
        });
        modal.modal('show');
    });
}
function updateItem(e) {
    var rowId = e.target.id;
    var type = e.currentTarget.id;
    var url="";
    var formInputs="";
    var modal="";
    if(type.match("uitgave")){
        url = "/restservices/dashboard/uitgave/update/";
        modal = $('.uitgaveTableModal');
        formInputs = $("#UitgaveForm input");
    } else {

        url = "/restservices/dashboard/inkomst/update/";
        modal = $('.inkomstTableModal');
        formInputs = $("#InkomstForm input");

    }
    var object = {};

    $.each(formInputs, function(index, input) {
        object["" + input.name + ""] = input.value;
    });
    object["inkomst_id"] = rowId;
    console.log(rowId);
    console.log(object);

    $.ajax({
        url: url + rowId,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(object),
        success: function(result) {
            //window.location.href = "../../dashboard.html";
        }
    });
}
function deleteItem(e) {
    var rowId = e.target.id;
    var type = e.currentTarget.id;

    var url="";
    if(type.match("uitgave")){
        url = "/restservices/dashboard/uitgave/delete/";
    } else {

        url = "/restservices/dashboard/inkomst/delete/";
    }

    $.ajax({
        url: url + rowId,
        type: "DELETE",
        success: function(result) {
            if(type.match("uitgave")) {
                loadUitgaves();
            } else {
                loadInkomsten();
            }
        }
    });
}
function itemOptions(e) {
    if (e.target.classList.contains("write")) alert("u about to adit");
    else if (e.target.classList.contains("ban")) deleteItem(e);
    else if (e.target.nodeName === "TD")getItem(e);

}

function loadTotalInkomsten() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/inkomst/getsum/" + user_id, function (data) {
        // console.log(data);
        $("#totalInkomst").empty();
        $("#totalInkomst").append(data['totaalInkomst']);
    });
}
function loadTotalUitgaves() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/uitgave/getsum/" + user_id, function (data) {
        // console.log(data);
        $("#totalUitgave").empty();
        $("#totalUitgave").append(data['totalUitgave']);
    });
}
function loadRestTotaal() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/total/getsum/" + user_id, function (data) {
        // console.log(data);
        $("#restTotaal").empty();
        $("#restTotaal").append(data['restTotal']);
    });
}