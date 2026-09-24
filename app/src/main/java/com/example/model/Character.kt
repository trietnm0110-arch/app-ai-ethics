package com.example.model

import androidx.compose.ui.graphics.Color

enum class CharacterId {
    ALEX,
    MIA,
    DR_LINH,
    AI_ASSISTANT,
    MINH,
    NARRATOR
}

data class Character(
    val id: CharacterId,
    val name: String,
    val roleTitle: String,
    val roleDescription: String,
    val primaryColor: Color,
    val badge: String,
    val avatarInitial: String
)

object Characters {
    val ALEX = Character(
        id = CharacterId.ALEX,
        name = "Alex",
        roleTitle = "Sinh viên năm nhất",
        roleDescription = "Rất hào hứng với công nghệ và AI, nhưng còn bỡ ngỡ trước các chuẩn mực học thuật khắt khe.",
        primaryColor = Color(0xFF38BDF8), // Cyan / Sky blue
        badge = "Freshman",
        avatarInitial = "A"
    )

    val MIA = Character(
        id = CharacterId.MIA,
        name = "Mia",
        roleTitle = "Sinh viên năm hai",
        roleDescription = "Học sinh chuyên cần, luôn đặt mục tiêu điểm số cao nhưng dễ bị quá tải bởi deadline dồn dập.",
        primaryColor = Color(0xFFF472B6), // Pink / Rose
        badge = "Honors Student",
        avatarInitial = "M"
    )

    val DR_LINH = Character(
        id = CharacterId.DR_LINH,
        name = "Dr. Linh",
        roleTitle = "Giảng viên & Cố vấn học thuật",
        roleDescription = "Nghiêm túc nhưng tận tâm. Luôn nhấn mạnh tính nguyên bản và năng lực tư duy độc lập của người học.",
        primaryColor = Color(0xFFA78BFA), // Violet
        badge = "Faculty / Mentor",
        avatarInitial = "L"
    )

    val AI_ASSISTANT = Character(
        id = CharacterId.AI_ASSISTANT,
        name = "AI Bot",
        roleTitle = "Trợ lý Trí tuệ Nhân tạo",
        roleDescription = "Phản hồi cực nhanh, đưa ra câu chữ trôi chảy nhưng đôi khi 'bịa' thông tin và không có lương tâm đạo đức.",
        primaryColor = Color(0xFF34D399), // Emerald / Mint
        badge = "Generative AI",
        avatarInitial = "AI"
    )

    val MINH = Character(
        id = CharacterId.MINH,
        name = "Minh",
        roleTitle = "Bạn cùng lớp thực tế",
        roleDescription = "Tìm kiếm các giải pháp thực dụng, tin rằng thời đại 4.0 phải tận dụng mọi công cụ để tiết kiệm thời gian.",
        primaryColor = Color(0xFFFBBF24), // Amber
        badge = "Peer",
        avatarInitial = "M"
    )

    val NARRATOR = Character(
        id = CharacterId.NARRATOR,
        name = "Người dẫn truyện",
        roleTitle = "Bối cảnh thực tế",
        roleDescription = "Quan sát viên khách quan ghi nhận các sự kiện diễn ra.",
        primaryColor = Color(0xFF94A3B8), // Slate
        badge = "Narrator",
        avatarInitial = "✦"
    )

    fun get(id: CharacterId): Character = when (id) {
        CharacterId.ALEX -> ALEX
        CharacterId.MIA -> MIA
        CharacterId.DR_LINH -> DR_LINH
        CharacterId.AI_ASSISTANT -> AI_ASSISTANT
        CharacterId.MINH -> MINH
        CharacterId.NARRATOR -> NARRATOR
    }
}
