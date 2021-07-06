let member = {
    init : function() {
        const _this = this;

        $('#btn-join').on('click', function() {
            _this.join();
        });
    },

    join : function() {

        const data = {
            email : $('#email').val(),
            password : $('#password').val(),
            role : $(':radio[name="role"]:checked').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/member',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)

        }).done(function(resData) {
            alert(resData.email + '님 회원가입!!');
            window.location.href = '/';

        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }

};

member.init();