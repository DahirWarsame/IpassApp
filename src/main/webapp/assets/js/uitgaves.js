// $(document).ready(initPage());
// function initPage() {
//     $("#addUitgaveForm").on("submit", function (e) {
//         e.preventDefault();
//         var userid = sessionStorage.getItem("sessionToken");
//         $.post({
//             url: "/restservices/dashboard/add/uitgave/" + userid,
//             data: $(this).serialize(),
//             success: function (data) {
//                 $('#uitgaveForm').modal('hide');
//             }
//         });
//     });
// }