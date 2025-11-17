-- Script SQL para criação das tabelas do sistema de Gamificação
-- Este script é gerado automaticamente pelo Hibernate, mas serve como referência

-- Tabela base para todas as entidades
-- CREATE TABLE base_entity_jpa (
--     id BINARY(16) NOT NULL,
--     created_at DATETIME(6) NOT NULL,
--     updated_at DATETIME(6) NOT NULL,
--     version BIGINT,
--     PRIMARY KEY (id)
-- );

-- Tabela de usuários
CREATE TABLE users (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_of_birth DATE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

-- Tabela de informações base
CREATE TABLE base_information (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATETIME(6),
    complete_date DATETIME(6),
    PRIMARY KEY (id)
);

-- Tabela de missões
CREATE TABLE missions (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATETIME(6),
    complete_date DATETIME(6),
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    difficulty_level INT DEFAULT 1,
    estimated_duration VARCHAR(40),
    points INT,
    PRIMARY KEY (id)
);

-- Tabela de objetivos
CREATE TABLE goals (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATETIME(6),
    complete_date DATETIME(6),
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    target_value DOUBLE,
    current_value DOUBLE DEFAULT 0.0,
    unit VARCHAR(50),
    mission_id BINARY(16),
    PRIMARY KEY (id),
    FOREIGN KEY (mission_id) REFERENCES missions(id)
);

-- Tabela de regras
CREATE TABLE rules (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    rule_type VARCHAR(50) NOT NULL,
    condition_expression TEXT,
    action_expression TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    priority INT DEFAULT 0,
    mission_id BINARY(16),
    PRIMARY KEY (id),
    FOREIGN KEY (mission_id) REFERENCES missions(id)
);

-- Tabela de recompensas
CREATE TABLE rewards (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    points INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    reward_type VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    expiration_days INT,
    max_uses_per_user INT,
    PRIMARY KEY (id)
);

-- Tabela de relacionamento usuário-missão
CREATE TABLE user_missions (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    user_id BINARY(16) NOT NULL,
    mission_id BINARY(16) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ASSIGNED',
    progress_percentage DOUBLE DEFAULT 0.0,
    started_at DATETIME(6),
    completed_at DATETIME(6),
    points_earned INT DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_mission (user_id, mission_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (mission_id) REFERENCES missions(id)
);

-- Tabela de relacionamento usuário-recompensa
CREATE TABLE user_rewards (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    user_id BINARY(16) NOT NULL,
    reward_id BINARY(16) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'EARNED',
    earned_at DATETIME(6) NOT NULL,
    used_at DATETIME(6),
    expires_at DATETIME(6),
    points_value INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (reward_id) REFERENCES rewards(id)
);

-- Tabela de progresso do usuário
CREATE TABLE user_progress (
    id BINARY(16) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    version BIGINT,
    user_id BINARY(16) NOT NULL,
    total_points INT NOT NULL DEFAULT 0,
    current_level INT NOT NULL DEFAULT 1,
    experience_points INT NOT NULL DEFAULT 0,
    badges_earned INT DEFAULT 0,
    missions_completed INT DEFAULT 0,
    goals_completed INT DEFAULT 0,
    last_activity_at DATETIME(6),
    streak_days INT DEFAULT 0,
    longest_streak_days INT DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Índices para melhor performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_active ON users(is_active);
CREATE INDEX idx_missions_status ON missions(status);
CREATE INDEX idx_goals_status ON goals(status);
CREATE INDEX idx_goals_mission ON goals(mission_id);
CREATE INDEX idx_rules_active ON rules(is_active);
CREATE INDEX idx_rules_mission ON rules(mission_id);
CREATE INDEX idx_user_missions_user ON user_missions(user_id);
CREATE INDEX idx_user_missions_mission ON user_missions(mission_id);
CREATE INDEX idx_user_missions_status ON user_missions(status);
CREATE INDEX idx_user_rewards_user ON user_rewards(user_id);
CREATE INDEX idx_user_rewards_status ON user_rewards(status);
CREATE INDEX idx_user_progress_user ON user_progress(user_id);
