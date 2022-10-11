package com.example.demo.task.fixtures;

import com.example.demo.model.User;

import java.util.Date;

class TaskResourceFixture {

    public static class Builder {

        private String title;
        private String status;
        private User owner;
        private Date createdAt;
        private Date updatedAt;
        private String description;

        public Builder valid() {
            return new Builder()
                    .setTitle("titulo")
                    .setStatus("ACTIVE")
                    .setDescription("LOREMIPSUM");
        }

        private Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        private Builder setStatus(String status) {
            this.status = status;
            return this;
        }
        private Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        private Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        private Builder setCreatedAt(Date createAt) {
            this.createdAt = createAt;
            return this;
        }

        private Builder setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

    }



    public Builder build() {
        return new TaskResourceFixture.Builder();
    }

}
