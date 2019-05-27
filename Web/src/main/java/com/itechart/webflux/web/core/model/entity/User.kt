package com.itechart.webflux.web.core.model.entity

import org.springframework.data.annotation.Id

class User
(@Id override var id: String?, var username: String?, var department: String?, var role: UserRole,
 var room: String?, var firstName: String?, var lastName: String?, var password: String?,
 var photo: String?, var email: String?, var age: Int?, var active: Boolean) : Entity {
    constructor(username: String?, role: UserRole) :
            this(null, username, null, role, null, null, null, null, null, null, null, true)
}
