CREATE TABLE IF NOT EXISTS account_history (
    ah_seq_no INT,
    account_no VARCHAR(255) NOT NULL,
    batch_id INT NOT NULL,
    waste_unit INT,
    effective_date TIMESTAMP NULL DEFAULT NULL,
    user_id VARCHAR(255),
    ah_comments TEXT,
    document_notes TEXT,
    service_area VARCHAR(255),
    status VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS account_history_search (
    ah_seq_no INT PRIMARY KEY,
    account_no VARCHAR(255),
    batch_id INT,
    waste_unit INT,
    user_id VARCHAR(255),
    service_area VARCHAR(255),
    status VARCHAR(255),
    from_status_date TIMESTAMP NULL DEFAULT NULL,
    to_status_date TIMESTAMP NULL DEFAULT NULL,
    from_effective_date TIMESTAMP NULL DEFAULT NULL,
    to_effective_date TIMESTAMP NULL DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS alluserdetails_view (
    user_id INT PRIMARY KEY,
    aur_id INT,
    au_adid VARCHAR(255),
    au_created_by VARCHAR(255),
    au_created_date TIMESTAMP NULL DEFAULT NULL,
    au_email_address VARCHAR(255),
    au_theme VARCHAR(255),
    au_updated_by VARCHAR(255),
    au_updated_date TIMESTAMP NULL DEFAULT NULL,
    au_name VARCHAR(255),
    au_active BOOLEAN,
    au_send_email BOOLEAN,
    role_id INT,
    role_name VARCHAR(255),
    department_id INT,
    department_name VARCHAR(255),
    default_role BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS application_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    adid VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMP NULL DEFAULT NULL,
    email_address VARCHAR(255),
    theme VARCHAR(255),
    updated_by VARCHAR(255),
    updated_date TIMESTAMP NULL DEFAULT NULL,
    name VARCHAR(255),
    active BOOLEAN,
    send_email BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    is_admin BOOLEAN,
    role_type VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    abbreviation VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS master_view (
    seq_no_pair VARCHAR(255) PRIMARY KEY,
    ah_seq_no INT,
    wbh_seq_no INT,
    account_no VARCHAR(255),
    batch_id INT,
    waste_unit INT,
    effective_date TIMESTAMP NULL DEFAULT NULL,
    user_id VARCHAR(255),
    ah_comments TEXT,
    document_notes TEXT,
    service_area VARCHAR(255),
    batch_type VARCHAR(255),
    status_dt TIMESTAMP NULL DEFAULT NULL,
    status VARCHAR(255),
    wbh_comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS master_search (
    seq_no_pair VARCHAR(255),
    ah_seq_no INT,
    wbh_seq_no INT,
    account_no VARCHAR(255),
    batch_id INT,
    waste_unit INT,
    user_id VARCHAR(255),
    service_area VARCHAR(255),
    batch_type VARCHAR(255),
    status VARCHAR(255),
    from_effective_date TIMESTAMP NULL DEFAULT NULL,
    to_effective_date TIMESTAMP NULL DEFAULT NULL,
    from_status_dt TIMESTAMP NULL DEFAULT NULL,
    to_status_dt TIMESTAMP NULL DEFAULT NULL,
    hide_old_batch_history BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS service_areas (
    seq_no INT AUTO_INCREMENT PRIMARY KEY,
    service_area VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS suspended_batches_view (
    batch_id INT PRIMARY KEY,
    suspension_effect_dt DATE,
    suspension_follow_dt DATE,
    account_no VARCHAR(255),
    waste_unit INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS waste_batch (
    batch_id INT AUTO_INCREMENT PRIMARY KEY,
    status_dt TIMESTAMP NULL DEFAULT NULL,
    status VARCHAR(255),
    user_id VARCHAR(255),
    wbh_comments VARCHAR(255),
    batch_type VARCHAR(255),
    suspension_effect_dt DATE,
    suspension_follow_dt DATE,
    suspension_reason VARCHAR(255),
    document_notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS waste_batch_history (
    seq_no INT AUTO_INCREMENT PRIMARY KEY,
    batch_id INT,
    status_dt TIMESTAMP NULL DEFAULT NULL,
    status VARCHAR(255),
    user_id VARCHAR(255),
    comments TEXT,
    batch_type VARCHAR(255),
    from_status_date TIMESTAMP NULL DEFAULT NULL,
    to_status_date TIMESTAMP NULL DEFAULT NULL,
    service_area INT,
    only_last_status BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);