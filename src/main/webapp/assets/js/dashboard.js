$(document).ready(initPage());

function initPage() {
    loadCountries();
}

//TODO : Labels, update, insert, delete

function loadCountries() {
    $.get("/restservices/dashboard", function (data) {
        var countryTable = $("#uitgaveTable");
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
                object.link +
                "</td>" +
                "<td>" +
                object.afbeelding +
                "</td>" +
                "<td>" +
                object.uitgave_datum +
                "</td>" +
                "<td class='delete'><i class='ban icon'></i><i class='write icon'></i></td>" +
                "</tr>";
            countryTable.append(String);
        });
    });
}