<%-- 
    Document   : layout_end
    Created on : Mar 18, 2026, 11:44:28 PM
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

</body>
</html>
