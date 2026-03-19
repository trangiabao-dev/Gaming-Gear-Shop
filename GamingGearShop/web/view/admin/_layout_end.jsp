<%-- 
    Document   : layout_end
    Created on : Mar 20, 2026, 1:06:09 AM
    Author     : ACER
--%>

</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
    // T? ??ng ?n toast sau 3 giây
    var toast = document.querySelector('.toast-msg');
    if (toast) {
        setTimeout(function () {
            toast.style.opacity = '0';
            setTimeout(function () { toast.remove(); }, 300);
        }, 3000);
    }

    // Hi?n th? tên file khi upload ?nh
    function updateFileName(input) {
        var display = document.getElementById('fileNameDisplay');
        if (input.files && input.files.length > 0) {
            display.textContent = '?ã ch?n: ' + input.files[0].name;
            display.style.color = '#16a34a';
        } else {
            display.textContent = '';
        }
    }

    // Select2 ? làm ??p dropdown
    $(document).ready(function () {
        $('select.form-control-admin').select2({
            width: '100%'
        });
    });
</script>

</body>
</html>
