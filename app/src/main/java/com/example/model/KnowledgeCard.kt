package com.example.model

data class KnowledgeCard(
    val id: String,
    val title: String,
    val englishTerm: String,
    val category: String,
    val summary: String,
    val corePrinciple: String,
    val realisticExample: String,
    val safeActionGuide: String,
    val iconName: String
)

object KnowledgeCardCatalog {
    val CARDS = listOf(
        KnowledgeCard(
            id = "card_plagiarism",
            title = "Đạo văn (Plagiarism)",
            englishTerm = "Plagiarism & Attribution",
            category = "Academic Integrity",
            summary = "Sử dụng ý tưởng, từ ngữ hoặc cấu trúc lập luận của người khác mà không ghi nhận nguồn thích hợp.",
            corePrinciple = "Trí tuệ học thuật tôn trọng quyền tác giả. Ngay cả khi bạn diễn đạt lại (paraphrase), nếu ý tưởng bắt nguồn từ một nguồn khác, việc trích dẫn đầy đủ là nghĩa vụ bắt buộc.",
            realisticExample = "Lấy nguyên một đoạn phân tích từ tài liệu hoặc AI rồi dán vào bài tiểu luận mà không có dấu ngoặc kép hoặc nguồn trích.",
            safeActionGuide = "Luôn ghi rõ nguồn (tên tác giả, năm xuất bản, trang). Nếu sử dụng AI hỗ trợ tổng hợp, hãy ghi chú quy trình và trích dẫn theo định dạng trường yêu cầu (APA, MLA, IEEE...).",
            iconName = "MenuBook"
        ),
        KnowledgeCard(
            id = "card_ai_assistance",
            title = "Trợ lý AI vs Làm hộ",
            englishTerm = "AI Assistance vs Cognitive Replacement",
            category = "Responsible AI",
            summary = "Việc sử dụng AI có thể được phép trong một số nhiệm vụ nhưng bị hạn chế hoặc cấm hoàn toàn trong nhiệm vụ khác.",
            corePrinciple = "Mục đích của việc học là rèn luyện tư duy của CHÍNH BẠN. AI có thể là người bạn đồng hành gợi mở góc nhìn (scaffolding), nhưng không thể là người thay thế bài làm của bạn.",
            realisticExample = "Dùng AI để giải thích một khái niệm khó hiểu (hợp lý) so với yêu cầu AI giải toàn bộ bài tập và nộp bài đó (vi phạm).",
            safeActionGuide = "Luôn đọc kỹ Syllabus của môn học. Khi nghi ngờ, hãy chủ động gửi email hỏi giảng viên trước khi nộp bài.",
            iconName = "SmartToy"
        ),
        KnowledgeCard(
            id = "card_ai_hallucination",
            title = "Ảo giác AI (Hallucination)",
            englishTerm = "AI Hallucination & Fake Citations",
            category = "Academic Rigor",
            summary = "AI ngôn ngữ lớn (LLM) là mô hình dự đoán từ tiếp theo, không phải kho lưu trữ chân lý. Nó có thể tự bịa ra thông tin và tài liệu khoa học giả mạo.",
            corePrinciple = "Người học chịu trách nhiệm 100% về tính chính xác của mọi thông tin xuất hiện trong bài làm của mình. 'Do AI bảo thế' không bao giờ là lời biện hộ hợp lệ.",
            realisticExample = "AI trích dẫn một bài báo 'Nguyen et al. (2023), Journal of Harvard Tech' nghe rất uy tín nhưng thực tế bài báo và tạp chí đó không hề tồn tại.",
            safeActionGuide = "Không bao giờ nộp tài liệu tham khảo nào mà bạn chưa tự tay mở bản gốc, đọc và kiểm tra DOI hoặc Google Scholar / Scopus.",
            iconName = "WarningAmber"
        ),
        KnowledgeCard(
            id = "card_disclosure",
            title = "Minh bạch & Khai báo AI",
            englishTerm = "Transparency & AI Disclosure",
            category = "Ethics & Honesty",
            summary = "Nhiều trường đại học yêu cầu sinh viên phải có phụ lục hoặc tuyên bố rõ ràng về mức độ và công cụ AI đã sử dụng.",
            corePrinciple = "Trung thực học thuật dựa trên sự minh bạch. Khai báo rõ ràng giúp giảng viên đánh giá đúng nỗ lực cá nhân và bảo vệ chính bạn trước các cáo buộc gian lận vô căn cứ.",
            realisticExample = "Kèm theo bảng kê: 'Tôi đã dùng Claude 3.5 để rà soát lỗi chính tả và gợi ý dàn ý phần 2. Toàn bộ nội dung phân tích do tôi tự viết.'",
            safeActionGuide = "Tạo một mục 'Tuyên bố sử dụng AI (AI Statement)' ở cuối bài nếu quy định môn học cho phép hỗ trợ có điều kiện.",
            iconName = "FactCheck"
        ),
        KnowledgeCard(
            id = "card_unauthorized_aid",
            title = "Hỗ trợ không được phép",
            englishTerm = "Unauthorized Assistance in Exams",
            category = "Academic Misconduct",
            summary = "Sử dụng tài liệu ngoài, người khác hoặc AI trong các kỳ thi, bài kiểm tra take-home khi quy chế không cho phép.",
            corePrinciple = "Kỳ thi là công cụ đo lường năng lực độc lập trong điều kiện tiêu chuẩn. Bất kỳ sự can thiệp trái phép nào đều làm sai lệch kết quả và bất công với những người học khác.",
            realisticExample = "Dán đề thi bài kiểm tra 60 phút vào chatbot để lấy đáp án trắc nghiệm hoặc gợi ý bài luận.",
            safeActionGuide = "Trong các kỳ thi, tắt toàn bộ tiện ích mở rộng AI. Đọc kỹ hướng dẫn của đề thi về tài liệu được phép mang vào.",
            iconName = "Gavel"
        ),
        KnowledgeCard(
            id = "card_fabrication",
            title = "Bịa đặt & Làm sai lệch (Fabrication)",
            englishTerm = "Data Fabrication & Falsification",
            category = "Scientific Integrity",
            summary = "Tự tạo ra dữ liệu nghiên cứu giả hoặc chỉnh sửa kết quả phân tích để phù hợp với giả thuyết mong muốn.",
            corePrinciple = "Nghiên cứu khoa học phải tôn trọng sự thật khách quan. Một kết quả sai sót hoặc bác bỏ giả thuyết vẫn có giá trị hơn kết quả bị làm giả hoàn hảo.",
            realisticExample = "Số liệu khảo sát thiếu 50 mẫu, người học yêu cầu AI sinh ngẫu nhiên 50 dòng số liệu để chạy SPSS.",
            safeActionGuide = "Lưu giữ toàn bộ dữ liệu thô (raw data) và nhật ký khảo sát. Nếu dữ liệu có hạn chế, hãy thẳng thắn trình bày trong phần Giới hạn nghiên cứu.",
            iconName = "Science"
        ),
        KnowledgeCard(
            id = "card_patchwriting",
            title = "Chắp vá câu chữ (Patchwriting)",
            englishTerm = "Patchwriting vs True Paraphrasing",
            category = "Writing Ethics",
            summary = "Chỉ thay thế một vài từ đồng nghĩa trong đoạn văn của AI hoặc của tác giả khác nhưng giữ nguyên cấu trúc gốc.",
            corePrinciple = "Diễn giải chân chính (Paraphrasing) đòi hỏi bạn phải thấu hiểu sâu sắc ý tưởng, sau đó tự mình cấu trúc lại bằng ngôn ngữ và tư duy của bản thân.",
            realisticExample = "Đoạn văn gốc: 'Biến đổi khí hậu đe dọa sinh kế ven biển' -> Sửa thành: 'Thời tiết thay đổi uy hiếp đời sống cư dân vùng duyên hải'.",
            safeActionGuide = "Đọc tài liệu, gấp lại, suy nghĩ trong 1 phút rồi tự viết ra sổ tay cách bạn giải thích điều đó cho một người bạn.",
            iconName = "EditNote"
        ),
        KnowledgeCard(
            id = "card_peer_collaboration",
            title = "Hợp tác nhóm & Đồng trách nhiệm",
            englishTerm = "Collaborative Ethics & Peer Trust",
            category = "Professional Conduct",
            summary = "Trong bài tập nhóm, sản phẩm nộp lên là trách nhiệm liên đới của tất cả thành viên.",
            corePrinciple = "Sự thiếu trung thực của một cá nhân có thể hủy hoại uy tín và điểm số của cả nhóm. Sự công khai và thỏa thuận nội bộ về quy chuẩn AI là chìa khóa.",
            realisticExample = "Một bạn bí mật nộp phần việc do AI làm 100%, khi giáo viên vấn đáp cả nhóm đều không hiểu nội dung đó.",
            safeActionGuide = "Thống nhất ngay từ buổi họp đầu tiên: Nhóm có được dùng AI không? Dùng vào bước nào? Mọi người đều phải hiểu phần việc của nhau.",
            iconName = "Groups"
        )
    )

    fun getById(id: String): KnowledgeCard? = CARDS.find { it.id == id }
}
