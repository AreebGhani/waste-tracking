window.table1 = null;
window.table2 = null;

if (window.table1 && typeof window.table1.fnDestroy === 'function') {
  window.table1.fnDestroy(); // Destroy existing instance if any
}
if (window.table2 && typeof window.table2.fnDestroy === 'function') {
  window.table2.fnDestroy(); // Destroy existing instance if any
}

if (typeof window.$ !== 'undefined' && window.$) {
  $(document).ready(function () {
    // DataTable initialization for #wasteBatchApproval
    if ($('#wasteBatchApproval').length) {
      if (window.table1 && typeof window.table1.fnDestroy === 'function') {
        window.table1.fnDestroy(); // Destroy existing instance if any
      }
      $('#wasteBatchApproval').dataTable().fnDestroy();
      window.table1 = $('#wasteBatchApproval').DataTable({
        bDestroy: true,
        bJQueryUI: true,
        bPaginate: true,
        bLengthChange: true,
        bFilter: true,
        bSort: true,
        bInfo: true,
        oLanguage: { sEmptyTable: 'No batch approval queue data to report!' },
        sPaginationType: 'full_numbers',
        aaSorting: [],
        bAutoWidth: false,
        iDisplayLength: 50,
      });
    }

    // DataTable initialization for #batchApproval
    if ($('#batchApproval').length) {
      if (window.table2 && typeof window.table2.fnDestroy === 'function') {
        window.table2.fnDestroy(); // Destroy existing instance if any
      }
      $('#batchApproval').dataTable().fnDestroy();
      window.table2 = $('#batchApproval').DataTable({
        bDestroy: true,
        bJQueryUI: true,
        bPaginate: true,
        bLengthChange: true,
        bFilter: true,
        bSort: true,
        bInfo: true,
        oLanguage: { sEmptyTable: 'No batch approval account data to report!' },
        sPaginationType: 'full_numbers',
        aaSorting: [[0, 'asc']],
        bAutoWidth: false,
        iDisplayLength: 50,
      });
    }
  });
}
