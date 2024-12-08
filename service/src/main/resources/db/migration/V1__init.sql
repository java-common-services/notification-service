-- Template Tables
CREATE TABLE template (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    content TEXT NOT NULL,
    template_type VARCHAR(20) NOT NULL,
    template_render_type VARCHAR(20) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE(name)
);

CREATE TABLE template_parameter (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    template_id VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    data_type VARCHAR(20) NOT NULL,
    data_validation_rule VARCHAR(500),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (template_id) REFERENCES template(id),
    CONSTRAINT UC_TemplateParameterName UNIQUE (template_id, name)
);


-- Template Rules Tables
CREATE TABLE template_rule (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    event_name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE (event_name)
);

-- Template rule with template mapping
CREATE TABLE template_rule_template_mapping (
    template_id VARCHAR(50) NOT NULL,
    template_rule_id VARCHAR(50) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (template_id, template_rule_id),
    FOREIGN KEY (template_id) REFERENCES template(id),
    FOREIGN KEY (template_rule_id) REFERENCES template_rule(id)
);
