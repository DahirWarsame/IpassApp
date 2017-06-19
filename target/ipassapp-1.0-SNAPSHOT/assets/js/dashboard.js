$(document).ready(initPage());
function initPage() {
    loadUitgaves();
    loadInkomsten();
    $('#addInkomstForm').on("submit", function (e) {
        addInkomst()
    });
    $('#addUitgaveButtonCancel').on("click", function (e) {
        $('#uitgaveForm').modal('hide');
    });
}

//TODO : Labels, update, insert, delete

function loadUitgaves() {
    var userid = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/get/uitgaves/"+ userid, function (data) {
        var uitgaveTable = $("#uitgaveTable");
        console.log(data);
        $.each(data, function (index, object) {
            var String =
                "<tr id='row" +
                index +
                "'>" +
                "<td>" +
                object.beschrijving +
                "</td>" +
                "<td>" +
                object.bedrag +
                "</td>" +
                "<td>" +
                object.soort_uitgave +
                "</td>" +
                "<td>" +
                object.uitgave_datum +
                "</td>" +
                "<td class='edit'><i class='ban icon'></i><i class='write icon'></i></td>" +
                "</tr>";
            uitgaveTable.append(String);
        });
    });
}
function loadInkomsten() {
    var user_id = sessionStorage.getItem("sessionToken");
    $.get("/restservices/dashboard/get/inkomsten/"+ user_id, function (data) {
        var inkomstTable = $("#inkomstTable");
        console.log(data);
        $.each(data, function (index, object) {
            var String =
                "<tr id='row" +
                index +
                "'>" +
                "<td>" +
                object.beschrijving +
                "</td>" +
                "<td>" +
                object.bedrag +
                "</td>" +
                "<td>" +
                object.soort_inkomst +
                "</td>" +
                "<td>" +
                object.inkomst_datum +
                "</td>" +
                "<td class='delete'><i class='ban icon'></i><i class='write icon'></i></td>" +
                "</tr>";
            inkomstTable.append(String);
        });
    });
}
function deleteUitgave(e) {
    var row = e.target.parentNode;
    var uitgaveId = row.firstChild.innerText;

    $.ajax({
        url: "/restservices/dashboard/uitgave/delete/" + uitgaveId,
        type: "DELETE",
        success: function(result) {
            loadUitgaves();
        }
    });
}
function deleteInkomst(e) {
    var row = e.target.parentNode;
    var inkosmtId = row.firstChild.innerText;

    $.ajax({
        url: "/restservices/dashboard/inkomst/delete/" + inkosmtId,
        type: "DELETE",
        success: function(result) {
            loadInkomsten();
        }
    });
}
function addUitgave() {
    // var object = {};
    //
    // var inputs = $("#addUitgaveForm input");
    // var dropdown = $("#addUitgaveForm select");
    //
    //
    //
    // $.each(inputs, function(index, input) {
    //     object["" + input.name + ""] = input.value;
    // });
    // object["" + dropdown[0].name + ""] = dropdown[0].value;
    // console.log(object);
    // $.ajax({
    //     url: "/restservices/dashboard/add/uitgave/"+userid,
    //     type: "POST",
    //     contentType: "application/json",
    //     data: JSON.stringify(object),
    //     success: function(result) {
    //         $('#uitgaveForm').modal('hide');
    //     }
    // });
    $.post({
        url: "/restservices/dashboard/add/uitgave/"+userid,
        data: $(this).serialize(),
        success: function (data) {
            $('#uitgaveForm').modal('hide');
        }
    });

}
function addInkomst(e) {
    var object = {};

    var inputs = $("#addInkomstForm input");
    var dropdown = $("#addInkomstForm select");

    var userid = sessionStorage.getItem("sessionToken");
    $.each(inputs, function(index, input) {
        object["" + input.name + ""] = input.value;
    });
    object["" + dropdown[0].name + ""] = dropdown[0].value;

    $.ajax({
        url: "/restservices/dashboard/add/inkomst/"+userid,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(object),
        success: function(result) {
            $('#inkomstForm').modal('hide');
        }
    });
}
function changeContent(e) {
    if (e.target.classList.contains("write")) console.log(e.target.nodeName);
    else if (e.target.classList.contains("ban")) deleteUitgave(e);
}