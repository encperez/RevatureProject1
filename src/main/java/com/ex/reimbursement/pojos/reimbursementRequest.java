package com.ex.reimbursement.pojos;

public class reimbursementRequest {
    private String employee;
    private String title;
    private String details;
    private String amount;
    private String status;
    private String approvedBy;

    public reimbursementRequest(String employee, String title, String details, String amount, String status, String approvedBy) {
        this.employee = employee;
        this.title = title;
        this.details = details;
        this.amount = amount;

        this.status = status;
        this.approvedBy = approvedBy;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedBy() { return approvedBy; }

    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }


    @Override
    public String toString() {
        return "reimbursementRequest{ " +
                "employee='" + employee + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", approvedBy='" + approvedBy + '\'' +
                '}';
    }
}
