        var authorisation = function () {
            var authJsonObject = {
                "login": document.getElementById("login").value,
                "password": document.getElementById("password").value
            };
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8443/api/admin/auth",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(authJsonObject),
                    dataType: 'json',
                    async: false,
                    success: function (authResult) {
                        if(sessionAuthResponseParser(authResult)) {
                            location.href = "http://localhost:8443/admin/main"
                        }
                        else alert("Авторизация НЕ была пройдена")
                    },
                    error: function (exception) {
                        alert("Error");
                    }
                })
                ;}