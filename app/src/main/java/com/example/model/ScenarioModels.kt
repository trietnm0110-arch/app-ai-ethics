package com.example.model

enum class DecisionType {
    RESPONSIBLE, // Tích cực, bảo vệ tính trung thực học thuật
    RISKY,       // Nguy cơ vi phạm cao hoặc lạm dụng AI
    GREY_ZONE    // Vùng xám, tùy thuộc quy định cụ thể và bối cảnh
}

data class DialogueLine(
    val speaker: CharacterId,
    val text: String,
    val emotion: String = "neutral" // neutral, anxious, confident, thoughtful, warning, curious
)

data class EthicsAnalysis(
    val yourDecision: String,
    val whatHappened: String,
    val whyDoesItMatter: String,
    val academicCategory: String,
    val howCouldYouHandleItBetter: String,
    val disclaimer: String = "Lưu ý quan trọng: Quy định cụ thể có thể khác nhau giữa từng trường, khoa và môn học. Hãy luôn chủ động kiểm tra syllabus, quy chế đào tạo hoặc tham vấn trực tiếp giảng viên phụ trách."
)

data class Consequence(
    val title: String,
    val narrative: String,
    val followUpDialogues: List<DialogueLine>,
    val outcomeBadge: String, // "Học hỏi tích cực", "Cảnh báo học vụ", "Giải trình khẩn cấp", "Vùng xám quy chế"
    val isPositive: Boolean
)

data class Choice(
    val id: String,
    val letter: String, // "A", "B", "C", "D"
    val title: String,
    val scoreDelta: Int, // e.g. +10, -15, +3, -5
    val decisionType: DecisionType,
    val consequence: Consequence,
    val ethicsAnalysis: EthicsAnalysis,
    val unlockedCardId: String? = null
)

data class Scenario(
    val id: String,
    val chapterId: Int,
    val number: Int,
    val title: String,
    val subtitle: String,
    val location: String,
    val summary: String,
    val initialDialogues: List<DialogueLine>,
    val dilemmaPrompt: String,
    val choices: List<Choice>,
    val associatedCardId: String
)

data class Chapter(
    val id: Int,
    val number: Int,
    val title: String,
    val subtitle: String,
    val description: String,
    val iconName: String,
    val scenarioIds: List<String>
)
