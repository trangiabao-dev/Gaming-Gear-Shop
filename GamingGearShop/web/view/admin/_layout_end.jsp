<%-- 
    Document   : layout_end
    Created on : Mar 20, 2026, 1:06:09 AM
    Author     : ACER
--%>

</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    var toast = document.querySelector('.toast-msg');
    if (toast) {
        setTimeout(function () {
            toast.style.opacity = '0';
            setTimeout(function () {
                toast.remove();
            }, 300);
        }, 3000);
    }
</script>
<script>
    function updateFileName(input) {
        var display = document.getElementById('fileNameDisplay');
        if (input.files && input.files.length > 0) {
            display.textContent = '?ã ch?n: ' + input.files[0].name;
            display.style.color = '#16a34a';
        } else {
            display.textContent = '';
        }
    }
</script>
</body>
</html>
