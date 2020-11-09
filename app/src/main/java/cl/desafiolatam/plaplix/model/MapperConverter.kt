package cl.desafiolatam.plaplix.model

import cl.desafiolatam.plaplix.model.database.PhoneDetailEntity
import cl.desafiolatam.plaplix.model.database.PhoneEntity
import cl.desafiolatam.plaplix.model.pojo.Phone
import cl.desafiolatam.plaplix.model.pojo.PhoneDetail

fun phoneConverter(phoneList: List<Phone>): List<PhoneEntity> {
    return phoneList.map { phone ->
        PhoneEntity(
            phone.id,
            phone.image,
            phone.name,
            phone.price
        )
    }
}

fun detailConverter(detail: PhoneDetail): PhoneDetailEntity {
    return PhoneDetailEntity(
        detail.id,
        detail.image,
        detail.name,
        detail.price,
        detail.credit,
        detail.description,
        detail.lastPrice
    )
}