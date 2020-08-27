(function () {
    function refreshCurrentUserTable() {
        $.get(`http://localhost:8080/api/users/me`)
            .done((currentUser) => {
                $("#currentUserTableBody")
                    .empty()
                    .append($('<tr>')
                        .append($('<td>').text(currentUser.id))
                        .append($('<td>').text(currentUser.firstName))
                        .append($('<td>').text(currentUser.lastName))
                        .append($('<td>').text(currentUser.age))
                        .append($('<td>').text(currentUser.email))
                        .append($('<td>').text(currentUser.rolesDisplayed))
                    );
            })
    }

    refreshCurrentUserTable();
})();
