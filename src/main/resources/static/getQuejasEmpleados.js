$(document).ready(function() {
    $('#queja').DataTable( {
        dom: 'lBfrtip',
        bDestroy: true,
        lengthMenu: [ [5,10, 25, 50, -1], [5,10, 25, 50, "All"] ],

    } );

} );