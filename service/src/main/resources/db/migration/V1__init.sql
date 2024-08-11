
CREATE TABLE template (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100),
    content TEXT NOT NULL,
    template_type VARCHAR(20) NOT NULL,
    template_render_type VARCHAR(20) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE(name)
);

CREATE TABLE template_parameter (
    id CHAR(36) NOT NULL PRIMARY KEY,
    template_id CHAR(36) NOT NULL,
    name VARCHAR(100) NOT NULL,
    data_type VARCHAR(20) NOT NULL,
    data_validation_rule VARCHAR(500),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (template_id) REFERENCES template(id),
    CONSTRAINT UC_TemplateParameterName UNIQUE (template_id, name)
)
