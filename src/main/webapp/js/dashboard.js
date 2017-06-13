$(document).ready(initPage());

function initPage() {
  loadCountries();
  $("#countryTable").click(changeContent);
}

//TODO : Labels, update, insert, delete

function loadUitgaves() {
  $.get("/restservices/dashboard", function(data) {
    var countryTable = $("#countryTable");
    $.each(data, function(index, object) {
      var String =
        "<tr id='row" +
        index +
        "'>" +
        "<td class='code_row'>" +
        object.code +
        "</td>" +
        "<td>" +
        object.name +
        "</td>" +
        "<td>" +
        object.capital +
        "</td>" +
        "<td>" +
        object.region +
        "</td>" +
        "<td class='delete'>&times;</td>" +
        "</tr>";
      countryTable.append(String);
    });
  });
}