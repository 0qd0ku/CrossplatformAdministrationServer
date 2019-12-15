        var authorisation = function () {
            var authJsonObject = {
                "login": document.getElementById("login").value,
                "password": document.getElementById("password").value
            };
                $.ajax({
                    type: "POST",
                    url: "https://localhost:8443/api/admin/auth",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(authJsonObject),
                    dataType: 'json',
                    async: false,
                    success: function (authResult) {
                        sessionAuthResponseParser(authResult)
                    },
                    error: function (exception) {
                        alert("Error:" + exception.responseText);
                    }
                })
                ;}