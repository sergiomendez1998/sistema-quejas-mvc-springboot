
    $(document).ready(function() {
    $('#usuarios').DataTable( {
        dom: 'lBfrtip',
        bDestroy: true,
        lengthMenu: [ [5,10, 25, 50, -1], [5,10, 25, 50, "All"] ],

    } );

} );