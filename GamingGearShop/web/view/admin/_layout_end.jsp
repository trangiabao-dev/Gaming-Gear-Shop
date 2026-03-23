</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script>
    var toast = document.querySelector('.toast-msg');
    if (toast) {
        setTimeout(function () {
            toast.style.opacity = '0';
            setTimeout(function () { toast.remove(); }, 300);
        }, 3000);
    }

    function updateFileName(input) {
        var display = document.getElementById('fileNameDisplay');
        if (input.files && input.files.length > 0) {
            display.textContent = '\u0110\u00e3 ch\u1ecdn: ' + input.files[0].name;
            display.style.color = '#16a34a';
        } else {
            display.textContent = '';
        }
    }

    $(document).ready(function () {
        $('select.form-control-admin').select2({
            width: '100%'
        });
    });
</script>

</body>
</html>