package com.example.model

object ScenarioRepositoryData {

    val CHAPTERS = listOf(
        Chapter(
            id = 1,
            number = 1,
            title = "The Deadline",
            subtitle = "Áp lực thời gian & Cám dỗ đường tắt",
            description = "Khi đồng hồ đếm ngược và bài tập chưa xong, liệu một cú nhấp chuột nhờ AI có phải là chiếc phao cứu sinh an toàn?",
            iconName = "Timer",
            scenarioIds = listOf("sc_1", "sc_2", "sc_3")
        ),
        Chapter(
            id = 2,
            number = 2,
            title = "The AI Assistant",
            subtitle = "Trợ lý sáng tạo hay Người làm hộ?",
            description = "Phân định ranh giới mong manh giữa gợi mở tư duy, paraphrase chân chính và hành vi 'chắp vá' văn bản AI.",
            iconName = "AutoAwesome",
            scenarioIds = listOf("sc_4", "sc_5", "sc_6")
        ),
        Chapter(
            id = 3,
            number = 3,
            title = "The Citation Trap",
            subtitle = "Cạm bẫy trích dẫn & Ảo giác học thuật",
            description = "Khám phá hiện tượng AI bịa đặt tài liệu khoa học và bài học xương máu về trách nhiệm xác thực dữ liệu.",
            iconName = "Search",
            scenarioIds = listOf("sc_7", "sc_8", "sc_9")
        ),
        Chapter(
            id = 4,
            number = 4,
            title = "The Exam",
            subtitle = "Kỳ thi & Hỗ trợ không được phép",
            description = "Khi đối mặt với bài thi take-home và quy chế thi cử, sự can thiệp của AI dẫn đến những hệ quả pháp lý học đường nào?",
            iconName = "Quiz",
            scenarioIds = listOf("sc_10", "sc_11", "sc_12")
        ),
        Chapter(
            id = 5,
            number = 5,
            title = "The Grey Zone",
            subtitle = "Vùng xám học thuật & Trách nhiệm cá nhân",
            description = "Những tình huống không có đáp án tuyệt đối trắng/đen: bài nhóm, dịch thuật, rà soát ngữ pháp và năng lực tự học.",
            iconName = "Balance",
            scenarioIds = listOf("sc_13", "sc_14", "sc_15")
        )
    )

    val SCENARIOS = listOf(
        // ==================== CHAPTER 1 ====================
        // Scenario 1
        Scenario(
            id = "sc_1",
            chapterId = 1,
            number = 1,
            title = "Phần mở bài lúc 23:00",
            subtitle = "Chỉ còn 2 tiếng trước hạn nộp bài tiểu luận",
            location = "Bàn học ký túc xá - 22:58",
            summary = "Alex đã viết xong toàn bộ phần thân bài phân tích thị trường tài chính, nhưng phần Mở đầu (Introduction) vẫn để trống. Đồng hồ điểm 23:00, hạn nộp cổng trường là 01:00 đêm.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Chết rồi, mắt mình díp lại rồi... Đã viết xong 2000 từ thân bài nhưng phần mở bài chưa có một chữ nào!", "anxious"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Chào Alex! Hãy nhập chủ đề của bạn vào đây, tôi có thể tạo một mở bài chuyên nghiệp đạt chuẩn APA chỉ trong 3 giây.", "confident"),
                DialogueLine(CharacterId.MINH, "Cậu còn lăn tăn gì nữa? 2 tiếng nữa khóa cổng nộp bài rồi! Cứ bảo bot viết rồi nộp đi, ai soi từng câu mở bài làm gì!", "neutral")
            ),
            dilemmaPrompt = "Bạn sẽ giải quyết phần mở bài như thế nào trước áp lực 2 tiếng cuối cùng?",
            choices = listOf(
                Choice(
                    id = "sc_1_c1",
                    letter = "A",
                    title = "Nhờ AI viết toàn bộ phần mở bài rồi copy nộp luôn",
                    scoreDelta = -15,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Giảng viên yêu cầu giải trình ý tưởng",
                        narrative = "Bài luận của bạn vượt qua hệ thống nộp bài, nhưng tuần sau Dr. Linh mời bạn lên văn phòng. Giọng văn mở bài sử dụng thuật ngữ triết học phức tạp mâu thuẫn hoàn toàn với lối diễn đạt giản dị ở thân bài. Bạn lúng túng không giải thích được luận điểm mở bài.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, tại sao phần mở bài của em lại trích dẫn lý thuyết cấu trúc luận mà em không hề nhắc lại hay phân tích ở thân bài?", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ... em thấy đoạn đó đọc vào nghe văn phong rất hàn lâm ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Bài làm của em có dấu hiệu sử dụng nội dung do AI tạo sinh mà không qua xử lý. Thầy sẽ tạm giữ bài để hội đồng xem xét.", "warning")
                        ),
                        outcomeBadge = "Yêu cầu giải trình",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Ủy thác 100% phần mở đầu cho AI và nộp nguyên văn không chỉnh sửa.",
                        whatHappened = "Giảng viên nhận ra sự đứt gãy về văn phong và chất vấn trực tiếp. Bạn không bảo vệ được tính nguyên bản.",
                        whyDoesItMatter = "Phần mở bài là nơi xác lập luận điểm (thesis statement) và phạm vi nghiên cứu của chính tác giả. Để AI viết hộ nghĩa là bạn đã để AI định hình tư duy của cả bài báo.",
                        academicCategory = "Làm bài hộ / Sử dụng tác phẩm của người khác (Contract Cheating / AI-Generated Work)",
                        howCouldYouHandleItBetter = "Hãy đọc phần thân bài mình đã viết, tóm lược câu hỏi cốt lõi thành 3 câu đơn giản: Bối cảnh, Vấn đề, và Giải pháp của bạn. Dù văn phong giản dị nhưng đó là tư duy của bạn."
                    ),
                    unlockedCardId = "card_plagiarism"
                ),
                Choice(
                    id = "sc_1_c2",
                    letter = "B",
                    title = "Nhờ AI gợi ý 3 hướng tiếp cận mở bài, sau đó tự viết lại bằng lời của mình",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Mở bài mạch lạc và bảo vệ thành công",
                        narrative = "AI gợi ý: (1) Bắt đầu bằng con số thống kê, (2) Bắt đầu bằng câu hỏi nghịch lý, (3) Bắt đầu bằng chính sách mới. Bạn chọn hướng nghịch lý, tự tay viết lại dựa trên các phân tích mình đã làm ở thân bài và thêm ghi chú sử dụng AI.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Gợi ý về nghịch lý thị trường rất thú vị, nhưng mình phải dùng đúng số liệu từ báo cáo của mình chứ không dùng số liệu của AI.", "thoughtful"),
                            DialogueLine(CharacterId.DR_LINH, "Bài của Alex có phần mở đầu vào đề rất sắc bén và gắn kết chặt chẽ với các chương sau. Làm tốt lắm!", "confident")
                        ),
                        outcomeBadge = "Thành công & Nguyên bản",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Sử dụng AI như công cụ kích thích ý tưởng (brainstorming), giữ vai trò kiểm soát nội dung và trực tiếp chấp bút.",
                        whatHappened = "Bài viết có mở đầu sáng tạo, liền mạch với thân bài và bạn hoàn toàn làm chủ kiến thức khi được hỏi.",
                        whyDoesItMatter = "Đây là phương pháp sử dụng AI có trách nhiệm (AI Scaffolding). AI chỉ mở rộng góc nhìn, quyết định và lao động trí tuệ vẫn thuộc về sinh viên.",
                        academicCategory = "Hỗ trợ học tập hợp lệ (Appropriate AI Assistance)",
                        howCouldYouHandleItBetter = "Tuyệt vời! Nếu trường bạn có quy định khai báo, hãy thêm một dòng ngắn ở phần phụ lục: 'Ý tưởng cấu trúc mở đầu có tham khảo gợi ý từ ChatGPT-4o'."
                    ),
                    unlockedCardId = "card_ai_assistance"
                ),
                Choice(
                    id = "sc_1_c3",
                    letter = "C",
                    title = "Lấy đoạn AI viết, dùng từ điển thay thế vài từ đồng nghĩa rồi nộp",
                    scoreDelta = -10,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Dính lỗi chắp vá (Patchwriting)",
                        narrative = "Hệ thống kiểm tra tính nguyên bản quét ra cấu trúc ngữ pháp trùng khớp với mẫu văn bản của mô hình ngôn ngữ lớn. Đoạn văn đọc gượng gạo vì các từ đồng nghĩa bị thay thế thiếu tự nhiên.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Alex ơi, câu này đọc lạ quá: 'Khí hậu thời tiết biến thiên uy hiếp sinh kế cư dân duyên hải'... Cậu dùng tool quay từ à?", "anxious"),
                            DialogueLine(CharacterId.ALEX, "Mình sợ bị quét AI nên thay vài chữ, ai dè đọc ngô nghê thế này...", "anxious")
                        ),
                        outcomeBadge = "Cảnh báo chất lượng",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Cố tình thay đổi từ ngữ bề mặt để che giấu nguồn gốc AI.",
                        whatHappened = "Bài viết trở nên khó hiểu, vẫn vi phạm bản chất của tính nguyên bản và để lại ấn tượng tiêu cực với người chấm.",
                        whyDoesItMatter = "Thay đổi từ đồng nghĩa mà không tái cấu trúc ý niệm gọi là 'Patchwriting'. Đây là hành vi lảng tránh tư duy chứ không tạo ra giá trị tri thức mới.",
                        academicCategory = "Chắp vá câu chữ / Đạo văn cấu trúc (Patchwriting)",
                        howCouldYouHandleItBetter = "Đừng tốn thời gian đổi từng chữ. Hãy đọc hiểu thông điệp cốt lõi, tắt màn hình AI đi và tự diễn giải lại theo cách hiểu của bạn."
                    ),
                    unlockedCardId = "card_patchwriting"
                ),
                Choice(
                    id = "sc_1_c4",
                    letter = "D",
                    title = "Không dùng AI, tự viết phần mở bài ngắn gọn 150 từ theo ý hiểu cá nhân",
                    scoreDelta = 10,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Bài viết mộc mạc nhưng chân thật",
                        narrative = "Bạn tập trung cao độ trong 45 phút, viết phần mở đầu thẳng thắn nêu rõ câu hỏi nghiên cứu và bố cục bài. Bài nộp đúng 23:55.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Tuy không bay bổng như AI viết, nhưng từng chữ đều là suy nghĩ thật của mình.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Mở bài mộc mạc, cô đọng nhưng đi thẳng vào trọng tâm vấn đề. Thầy đánh giá cao tính chân thực này.", "confident")
                        ),
                        outcomeBadge = "Nguyên bản 100%",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Từ chối dùng đường tắt công nghệ, tự lực hoàn thành nhiệm vụ dù chịu áp lực thời gian.",
                        whatHappened = "Bạn rèn luyện được năng lực viết dưới áp lực và hoàn toàn yên tâm về sự trong sạch học thuật.",
                        whyDoesItMatter = "Tự viết giúp bạn nhìn lại toàn bộ mạch lập luận của bài, từ đó phát hiện những điểm chưa hợp lý trong phần thân bài.",
                        academicCategory = "Liêm chính học thuật tuyệt đối (Academic Integrity)",
                        howCouldYouHandleItBetter = "Để tránh áp lực lần sau, hãy lập thói quen phác thảo mở bài sơ lược ngay từ khi bắt đầu làm đề cương."
                    ),
                    unlockedCardId = "card_plagiarism"
                )
            ),
            associatedCardId = "card_plagiarism"
        ),

        // Scenario 2
        Scenario(
            id = "sc_2",
            chapterId = 1,
            number = 2,
            title = "10 phút trước giờ G",
            subtitle = "Phát hiện số liệu AI đưa ra có điểm bất thường",
            location = "Phòng máy tính - 23:50",
            summary = "Chỉ còn 10 phút trước hạn chót. Khi đọc lại bài lần cuối, Mia phát hiện một con số thống kê mà AI cung cấp về tỷ lệ lạm phát năm 2021 là 18.5% - dường như vô lý và quá cao so với thực tế.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MIA, "Trời ơi, 10 phút nữa nộp bài rồi! Đoạn AI gợi ý trích dẫn bảo lạm phát Việt Nam năm 2021 là 18.5%, sao nghe vô lý thế này?!", "anxious"),
                DialogueLine(CharacterId.MINH, "Kệ đi Mia, nộp ngay kẻo muộn! Deadline muộn 1 phút là mất điểm chuyên cần đấy! Chắc thầy cô cũng chỉ lướt qua thôi.", "neutral"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Dữ liệu được tổng hợp từ nguồn uy tín. Tuy nhiên tôi có thể mắc sai sót nhỏ trong việc truy xuất thời gian thực.", "warning")
            ),
            dilemmaPrompt = "Bạn sẽ làm gì khi phát hiện dữ liệu AI có dấu hiệu sai lệch lúc sát giờ nộp bài?",
            choices = listOf(
                Choice(
                    id = "sc_2_c1",
                    letter = "A",
                    title = "Nhắm mắt để nguyên số liệu và nộp ngay để không trễ deadline",
                    scoreDelta = -15,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị trừ điểm nặng vì đưa thông tin sai sự thật",
                        narrative = "Dr. Linh ngay lập tức khoanh đỏ con số 18.5%. Trong buổi phản biện, thầy yêu cầu Mia đưa ra nguồn gốc của số liệu này. Mia không thể cung cấp được báo cáo chính thức của Tổng cục Thống kê.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Năm 2021 lạm phát Việt Nam được kiểm soát dưới 2%. Con số 18.5% này em lấy từ đâu ra?", "warning"),
                            DialogueLine(CharacterId.MIA, "Em... em lấy từ gợi ý của một công cụ tổng hợp tài liệu ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Đưa số liệu sai lệch vào bài nghiên cứu mà không kiểm chứng là lỗi nghiêm trọng về phương pháp học thuật.", "warning")
                        ),
                        outcomeBadge = "Trừ điểm dữ liệu sai",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Chấp nhận nộp thông tin nghi vấn để chạy theo hạn chót.",
                        whatHappened = "Số liệu sai bị phát hiện ngay lập tức, làm mất uy tín toàn bộ bài phân tích dù các phần khác làm rất tốt.",
                        whyDoesItMatter = "Trong học thuật và khoa học, tính chính xác của dữ liệu là tối thượng. Lan truyền thông tin sai lệch (misinformation) làm xói mòn giá trị của nghiên cứu.",
                        academicCategory = "Làm sai lệch thông tin / Thiếu kiểm chứng (Falsification / Negligence)",
                        howCouldYouHandleItBetter = "Khi không thể kiểm chứng kịp thời, giải pháp an toàn nhất là LOẠI BỎ khẳng định cụ thể đó hoặc diễn đạt ở mức định tính chung."
                    ),
                    unlockedCardId = "card_ai_hallucination"
                ),
                Choice(
                    id = "sc_2_c2",
                    letter = "B",
                    title = "Xóa con số cụ thể, thay bằng câu phân tích định tính an toàn và nộp bài",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Xử lý khủng hoảng thông minh và an toàn",
                        narrative = "Mia nhanh chóng sửa thành: 'Trong giai đoạn 2021, áp lực lạm phát chịu ảnh hưởng lớn từ chuỗi cung ứng toàn cầu'. Bài nộp trước deadline 3 phút.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "May quá, thà viết câu an toàn còn hơn đưa con số rác vào bài nghiên cứu.", "thoughtful"),
                            DialogueLine(CharacterId.DR_LINH, "Nhận định tổng quan rất hợp lý. Lập luận an toàn và có cơ sở.", "confident")
                        ),
                        outcomeBadge = "Bảo vệ độ tin cậy",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Ưu tiên độ tin cậy của bài viết hơn là sự xuất hiện của những con số chưa được kiểm chứng.",
                        whatHappened = "Bài viết không bị dính 'ảo giác AI' và bạn kịp nộp bài đúng hạn một cách an toàn.",
                        whyDoesItMatter = "Kỹ năng phản biện trước kết quả AI (Critical Verification) là phẩm chất quan trọng nhất của người học trong kỷ nguyên số.",
                        academicCategory = "Kiểm chứng thông tin học thuật (Verification Mandate)",
                        howCouldYouHandleItBetter = "Hành động kịp thời và rất chính xác! Sau khi nộp, bạn có thể tra cứu lại số liệu chuẩn của Tổng cục Thống kê để chuẩn bị cho buổi thuyết trình."
                    ),
                    unlockedCardId = "card_ai_hallucination"
                ),
                Choice(
                    id = "sc_2_c3",
                    letter = "C",
                    title = "Dừng lại 2 phút để tra nhanh Google từ trang Tổng cục Thống kê (.gov.vn)",
                    scoreDelta = 15,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Sửa đúng số liệu chính thức chỉ trong 2 phút",
                        narrative = "Mia gõ 'CPI Việt Nam 2021 gso.gov.vn' và tìm thấy con số chuẩn là 1.84%. Mia sửa lại vào bài và kịp nộp lúc 23:57.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Con số thật là 1.84%, khác một trời một vực với 18.5% của AI! Suýt nữa thì mang họa!", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Trích dẫn nguồn GSO rất chuẩn xác và cập nhật. Điểm cộng cho sự cẩn trọng!", "confident")
                        ),
                        outcomeBadge = "Kiểm chứng xuất sắc",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Dùng kỹ năng tra cứu thông tin gốc từ cơ quan có thẩm quyền.",
                        whatHappened = "Số liệu được đính chính hoàn hảo từ nguồn sơ cấp (primary source). Bài làm vừa kịp giờ vừa chính xác tuyệt đối.",
                        whyDoesItMatter = "Biến AI thành điểm khởi đầu cho việc tìm kiếm, nhưng luôn đối chiếu với nguồn chính thống trước khi xuất bản kết quả.",
                        academicCategory = "Sử dụng nguồn tài liệu chính thống (Primary Sourcing)",
                        howCouldYouHandleItBetter = "Đây là chuẩn mực vàng trong nghiên cứu học thuật! Luôn truy nguyên về nguồn gốc xuất bản gốc."
                    ),
                    unlockedCardId = "card_ai_hallucination"
                )
            ),
            associatedCardId = "card_ai_hallucination"
        ),

        // Scenario 3
        Scenario(
            id = "sc_3",
            chapterId = 1,
            number = 3,
            title = "Bài toán tối ưu hóc búa",
            subtitle = "Bài tập giải tích / kinh tế lượng vượt quá khả năng",
            location = "Thư viện trường - 16:30",
            summary = "Alex gặp một bài tập mô hình hóa toán kinh tế rất khó. Đã thử làm 3 tiếng nhưng bế tắc. Một bạn trong lớp khuyên dùng công cụ AI chuyên giải toán đại học để lấy đáp án từng bước rồi nộp.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Đề bài yêu cầu đạo hàm bậc hai ma trận Hessian để tìm điểm dừng... Mình đọc sách mãi mà không hiểu cách biến đổi.", "anxious"),
                DialogueLine(CharacterId.MINH, "Cậu chụp ảnh đề bài ném vào AI đi! Nó giải chi tiết từng bước, ra kết quả đẹp như sách giáo khoa!", "neutral"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Tôi đã giải xong bài toán. Đạo hàm cực trị đạt tại x=3.5, y=-1.2. Bạn có thể sao chép toàn bộ mã LaTeX hoặc lời giải này.", "confident")
            ),
            dilemmaPrompt = "Khi gặp bài tập quá khó mà AI giải sẵn từng bước, bạn lựa chọn hướng đi nào?",
            choices = listOf(
                Choice(
                    id = "sc_3_c1",
                    letter = "A",
                    title = "Chép lại nguyên vẹn lời giải từng bước của AI và nộp bài",
                    scoreDelta = -15,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bế tắc hoàn toàn khi lên bảng kiểm tra miệng",
                        narrative = "Alex đạt 10/10 bài tập về nhà. Nhưng hôm sau tại lớp, Dr. Linh gọi Alex lên bảng giải thích bước chuyển đổi từ dòng 4 sang dòng 5. Alex đứng chết lặng vì không hiểu tại sao AI lại dùng định lý Taylor mở rộng.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, tại sao ở đây em lại khai triển ma trận bán xác định dương? Em áp dụng định lý nào?", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ... em xem trên mạng và làm theo chứ chưa hiểu sâu ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Mục tiêu bài tập là để các em luyện kỹ năng tính toán. Nộp lời giải mà không hiểu bản chất là tự tước đi năng lực của mình.", "warning")
                        ),
                        outcomeBadge = "Không vượt qua kiểm tra",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Nộp bài làm không phải của bản thân và không hiểu bản chất lời giải.",
                        whatHappened = "Điểm số ảo không thể bù đắp lỗ hổng kiến thức thực tế. Mất điểm tin tưởng trước giảng viên.",
                        whyDoesItMatter = "Hành vi này tương đương với 'nhờ người khác làm bài hộ'. Việc học bị thay thế bởi sự sao chép thụ động.",
                        academicCategory = "Gian lận học thuật / Làm bài hộ (Academic Misconduct / Proxy Work)",
                        howCouldYouHandleItBetter = "Hãy yêu cầu AI: 'Đừng giải bài này cho tôi. Hãy gợi ý cho tôi định lý cần dùng và giải thích khái niệm ma trận Hessian bằng ví dụ đơn giản'."
                    ),
                    unlockedCardId = "card_ai_assistance"
                ),
                Choice(
                    id = "sc_3_c2",
                    letter = "B",
                    title = "Dùng AI giải thích từng khái niệm chưa hiểu, tự mình tính toán lại trên giấy nháp",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Thực sự làm chủ bài toán khó",
                        narrative = "Alex hỏi AI về trực quan hình học của ma trận Hessian. Sau khi hiểu bản chất điểm yên ngựa (saddle point), Alex tự tay đặt bút giải từ đầu đến cuối.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "À! Hóa ra nó giống như một cái yên ngựa! Bây giờ mình đã hiểu tại sao phải xét dấu định thức!", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Bài làm có một vài lỗi tính toán nhỏ ở phép nhân ma trận, nhưng tư duy giải quyết vấn đề của em rất chuẩn xác!", "confident")
                        ),
                        outcomeBadge = "Tiếp thu tri thức thực thụ",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Biến AI thành người gia sư kiên nhẫn giảng giải khái niệm thay vì cỗ máy in đáp án.",
                        whatHappened = "Bạn hiểu được bản chất toán học, tự vượt qua bài tập và nhớ lâu kiến thức cho kỳ thi cuối kỳ.",
                        whyDoesItMatter = "Đây là định nghĩa cốt lõi của 'AI for Learning' - hỗ trợ nâng cao nhận thức của con người chứ không thay thế con người.",
                        academicCategory = "Sử dụng AI có trách nhiệm (Responsible AI Usage)",
                        howCouldYouHandleItBetter = "Đây chính là phương pháp học tập mẫu mực! Bạn có thể lưu lại các ghi chú này vào tài liệu ôn thi."
                    ),
                    unlockedCardId = "card_ai_assistance"
                ),
                Choice(
                    id = "sc_3_c3",
                    letter = "C",
                    title = "Gửi email xin gia hạn hoặc đến văn phòng hỏi trợ giảng (TA) hướng dẫn",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Nhận được sự trợ giúp tận tình từ trợ giảng",
                        narrative = "Trợ giảng chỉ cho Alex điểm nghẽn trong cách đặt ẩn số. Alex nộp bài muộn nửa ngày nhưng kèm theo lời giải thích trung thực.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Cảm ơn anh trợ giảng đã chỉ ra em bị nhầm dấu ở phương trình số 2!", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Thầy hoan nghênh tinh thần chủ động hỏi bài khi gặp khó khăn. Đó là thái độ nghiên cứu nghiêm túc.", "confident")
                        ),
                        outcomeBadge = "Chủ động học hỏi",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Tìm kiếm sự hỗ trợ học thuật chính thống từ hệ sinh thái nhà trường.",
                        whatHappened = "Giảng viên và trợ giảng nắm được khó khăn của sinh viên và kịp thời bổ sung kiến thức.",
                        whyDoesItMatter = "Dám thừa nhận mình chưa hiểu là bước đầu tiên của sự trung thực trong học thuật.",
                        academicCategory = "Liêm chính học thuật (Academic Integrity)",
                        howCouldYouHandleItBetter = "Rất tốt. Hãy chủ động lập nhóm học tập với các bạn trong lớp để cùng nhau giải các bài toán khó."
                    ),
                    unlockedCardId = "card_peer_collaboration"
                )
            ),
            associatedCardId = "card_ai_assistance"
        ),

        // ==================== CHAPTER 2 ====================
        // Scenario 4
        Scenario(
            id = "sc_4",
            chapterId = 2,
            number = 4,
            title = "Ngọn lửa Brainstorming",
            subtitle = "Tìm kiếm đề tài nghiên cứu phương pháp định lượng",
            location = "Quán cà phê sinh viên - 09:00",
            summary = "Mia đang tìm kiếm đề tài cho bài tiểu luận môn Xã hội học. AI đưa ra 10 đề tài mới lạ về tác động của mạng xã hội đối với hội chứng FOMO của Gen Z.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MIA, "Mình nghĩ mãi không ra một góc nhìn mới cho bài tiểu luận xã hội học...", "thoughtful"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Đây là 5 chủ đề độc đáo kết hợp giữa kinh tế vi mô và tâm lý học hành vi mà bạn có thể tham khảo!", "confident"),
                DialogueLine(CharacterId.MINH, "Hay đấy, chọn luôn đề tài số 3 đi, rồi bảo nó lập luôn dàn ý chi tiết!", "neutral")
            ),
            dilemmaPrompt = "Sử dụng AI trong giai đoạn phát triển ý tưởng (Brainstorming) như thế nào là phù hợp?",
            choices = listOf(
                Choice(
                    id = "sc_4_c1",
                    letter = "A",
                    title = "Nhờ AI đưa ra các ý tưởng gợi ý, chọn 1 ý tưởng rồi tự tìm kiếm tài liệu thực tế và tự viết đề cương",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Đề tài sáng tạo và thuyết phục",
                        narrative = "Mia chọn góc nhìn về 'FOMO và áp lực chi tiêu', sau đó tự lên thư viện tìm các bài báo của tác giả Việt Nam và viết đề cương nghiên cứu bài bản.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Ý tưởng tiếp cận này rất mới mẻ và có tính thời sự cao. Đề cương của em xây dựng rất chặt chẽ.", "confident"),
                            DialogueLine(CharacterId.MIA, "Em cảm ơn thầy! Em đã tham khảo góc nhìn gợi ý từ AI và sau đó đào sâu nghiên cứu tài liệu thực tế ạ.", "confident")
                        ),
                        outcomeBadge = "Sáng tạo hợp lệ",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Dùng AI để phá vỡ bế tắc ý tưởng ban đầu (Writer's block), nhưng tự mình phát triển nghiên cứu.",
                        whatHappened = "Bạn tạo ra một công trình độc đáo mang dấu ấn cá nhân, đồng thời tự tin giải trình nguồn cảm hứng.",
                        whyDoesItMatter = "Brainstorming với AI hoàn toàn hợp lệ ở hầu hết các trường đại học, miễn là người học không để AI tạo sinh nội dung văn bản cuối cùng.",
                        academicCategory = "Hỗ trợ phát triển ý tưởng (Idea Exploration)",
                        howCouldYouHandleItBetter = "Ghi chú lại câu lệnh (prompt) bạn đã dùng để làm nhật ký nghiên cứu nếu môn học yêu cầu nộp phụ lục phương pháp."
                    ),
                    unlockedCardId = "card_disclosure"
                ),
                Choice(
                    id = "sc_4_c2",
                    letter = "B",
                    title = "Yêu cầu AI viết sẵn toàn bộ đề cương chi tiết, các luận cứ và chỉ việc nộp bản đề cương đó",
                    scoreDelta = -12,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Đề cương rỗng và thiếu khả thi",
                        narrative = "Đề cương AI viết nghe rất kêu nhưng các phương pháp thu thập dữ liệu hoàn toàn xa rời thực tế ở Việt Nam. Khi Dr. Linh hỏi về phương án phỏng vấn sâu, Mia không trả lời được.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Đề cương ghi em sẽ khảo sát 10,000 mẫu ngẫu nhiên phân tầng trong 2 tuần? Em có hiểu để làm được việc này cần kinh phí và nhân lực thế nào không?", "warning"),
                            DialogueLine(CharacterId.MIA, "Dạ... em không tính toán kỹ tính khả thi ạ...", "anxious")
                        ),
                        outcomeBadge = "Đề cương thiếu thực tế",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Ủy thác việc lập dàn ý và phương pháp luận cho AI.",
                        whatHappened = "Đề cương mang tính sách vở, phi thực tế và bộc lộ rõ việc người viết không suy nghĩ thấu đáo về khả năng thực thi.",
                        whyDoesItMatter = "Kỹ năng lập đề cương nghiên cứu là bài kiểm tra quan trọng nhất về tư duy logic. Đánh mất bước này khiến bạn không thể làm các bước sau.",
                        academicCategory = "Ủy thác tư duy (Cognitive Offloading / Misconduct)",
                        howCouldYouHandleItBetter = "Chỉ dùng gợi ý của AI làm tài liệu tham khảo ban đầu. Luôn đặt câu hỏi: 'Mình có nguồn lực và kiến thức để tự làm nghiên cứu này không?'"
                    ),
                    unlockedCardId = "card_ai_assistance"
                )
            ),
            associatedCardId = "card_disclosure"
        ),

        // Scenario 5
        Scenario(
            id = "sc_5",
            chapterId = 2,
            number = 5,
            title = "Cái bẫy Patchwriting",
            subtitle = "Copy nội dung AI và thay đổi một vài từ ngữ",
            location = "Phòng tự học - 14:15",
            summary = "Alex nhờ AI viết một đoạn giải thích về Chu kỳ kinh tế. Thấy đoạn văn quá chuẩn xác, Alex copy nguyên đoạn rồi mở từ điển tìm từ đồng nghĩa: đổi 'tăng trưởng' thành 'phát triển', 'suy thoái' thành 'suy giảm'.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Đoạn này AI viết hay quá, từ ngữ học thuật chuẩn chỉ. Nếu copy nguyên thì sợ bị phần mềm quét AI bắt được...", "thoughtful"),
                DialogueLine(CharacterId.MINH, "Mẹo này xưa như Trái Đất: Cậu đổi mấy động từ chính sang từ đồng nghĩa, đảo vài vế câu là phần mềm quét đạo văn chào thua ngay!", "confident"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Nội dung của tôi là văn bản độc nhất, nhưng cấu trúc cú pháp vẫn mang đặc trưng của mô hình thống kê ngôn ngữ.", "neutral")
            ),
            dilemmaPrompt = "Thay đổi một vài từ đồng nghĩa trong đoạn văn của AI có được xem là bài viết của bạn?",
            choices = listOf(
                Choice(
                    id = "sc_5_c1",
                    letter = "A",
                    title = "Thay đổi một số từ đồng nghĩa và đảo trật tự câu để vượt qua công cụ kiểm tra",
                    scoreDelta = -15,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị hội đồng học thuật chỉ trích vì hành vi né tránh",
                        narrative = "Dr. Linh nhận thấy đoạn văn có nhiều từ ngữ chắp vá kỳ lạ, mất đi tính tự nhiên của tiếng Việt. Khi so sánh cấu trúc ngữ pháp, hệ thống phát hiện đây là văn bản phái sinh từ AI được cố tình xáo trộn.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Thay đổi từ đồng nghĩa không biến tác phẩm của người khác hoặc của AI thành của em. Đây là lỗi Patchwriting kinh điển.", "warning"),
                            DialogueLine(CharacterId.ALEX, "Em tưởng chỉ khi nào copy nguyên xi 100% thì mới tính là vi phạm ạ?", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Vi phạm bản quyền tư duy không nằm ở mặt chữ, mà nằm ở việc chiếm đoạt cấu trúc lập luận mà không đóng góp nỗ lực sáng tạo.", "warning")
                        ),
                        outcomeBadge = "Vi phạm Patchwriting",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Áp dụng kỹ thuật thay từ bề mặt nhằm che giấu nguồn gốc AI.",
                        whatHappened = "Bị giảng viên vạch trần lỗi chắp vá văn bản. Bị yêu cầu viết lại toàn bộ chương với điểm tối đa bị giới hạn.",
                        whyDoesItMatter = "Patchwriting là hình thức đạo văn tinh vi nhưng nguy hiểm vì nó tạo ra ảo tưởng rằng người học 'đã làm việc', trong khi thực chất chỉ là thao tác cơ học.",
                        academicCategory = "Chắp vá câu chữ (Patchwriting / Plagiarism)",
                        howCouldYouHandleItBetter = "Thực hành phương pháp 3 bước: (1) Đọc đoạn văn AI gợi ý để hiểu ý niệm, (2) Đóng màn hình lại, (3) Tự viết ra giấy theo văn phong tự nhiên của một sinh viên đại học."
                    ),
                    unlockedCardId = "card_patchwriting"
                ),
                Choice(
                    id = "sc_5_c2",
                    letter = "B",
                    title = "Đọc hiểu bản chất, tự diễn đạt lại bằng ngôn từ và ví dụ thực tế của riêng mình",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Được khen ngợi vì ví dụ sinh động",
                        narrative = "Alex hiểu chu kỳ kinh tế và đưa ví dụ về sự biến động của ngành du lịch Việt Nam sau đại dịch. Đoạn văn mang đậm dấu ấn cá nhân và góc nhìn riêng.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Khi tự liên hệ với ngành du lịch, mình thấy bài viết có hồn hơn hẳn những câu chữ chung chung của AI.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Rất tốt! Việc đưa ví dụ thực tế từ bối cảnh trong nước chứng minh em đã thực sự thấu hiểu lý thuyết.", "confident")
                        ),
                        outcomeBadge = "Hiểu sâu & Sáng tạo",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Chuyển hóa thông tin thành tri thức cá nhân thông qua việc liên hệ thực tế.",
                        whatHappened = "Bài viết mang tính thuyết phục cao, văn phong mạch lạc và không thể bị nhầm lẫn với sản phẩm AI vô hồn.",
                        whyDoesItMatter = "Đây chính là giá trị cốt lõi của giáo dục đại học: khả năng nội tâm hóa kiến thức lý thuyết và ứng dụng vào thực tiễn cuộc sống.",
                        academicCategory = "Diễn giải chân chính & Tư duy phản biện (True Paraphrasing & Critical Thinking)",
                        howCouldYouHandleItBetter = "Tuyệt vời. Đây là cách làm việc chuyên nghiệp mà mọi nhà nghiên cứu và chuyên gia đều áp dụng."
                    ),
                    unlockedCardId = "card_patchwriting"
                )
            ),
            associatedCardId = "card_patchwriting"
        ),

        // Scenario 6
        Scenario(
            id = "sc_6",
            chapterId = 2,
            number = 6,
            title = "Paraphrase mù quáng",
            subtitle = "Dùng AI viết lại một tài liệu mà không mở đọc bản gốc",
            location = "Thư viện trường - 11:30",
            summary = "Minh tìm thấy một bài báo tiếng Anh dài 30 trang về Trách nhiệm xã hội của doanh nghiệp (CSR). Vì ngại đọc, Minh dán tóm tắt trừu tượng vào AI và bảo: 'Viết lại đoạn này thành 500 từ phân tích nộp bài'.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MINH, "Bài báo 30 trang toàn thuật ngữ kinh tế, đọc đến sáng mai cũng không xong. Cứ ném vào AI bảo nó tóm tắt rồi paraphrase lại là xong bài!", "neutral"),
                DialogueLine(CharacterId.MIA, "Minh ơi, cậu không đọc bản gốc nhỡ tác giả có kết luận trái ngược hoặc có điều kiện loại trừ thì sao?", "anxious"),
                DialogueLine(CharacterId.MINH, "Lo xa quá, AI bây giờ đọc hiểu siêu lắm, nó tóm tắt chuẩn 100% rồi!", "confident")
            ),
            dilemmaPrompt = "Dùng AI diễn giải lại một nguồn tài liệu mà người viết chưa từng mở ra đọc có rủi ro gì?",
            choices = listOf(
                Choice(
                    id = "sc_6_c1",
                    letter = "A",
                    title = "Nộp đoạn tóm tắt paraphrase của AI và trích dẫn tên tác giả bài báo gốc",
                    scoreDelta = -12,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Hiểu sai hoàn toàn luận điểm của tác giả gốc",
                        narrative = "Thực tế bài báo gốc phê phán mô hình CSR kiểu hình thức, nhưng đoạn tóm tắt bề mặt của AI lại ca ngợi mô hình đó. Minh bị đánh trượt vì gán ghép kết luận sai cho tác giả nổi tiếng.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Minh, tác giả Porter trong bài báo này kịch liệt phản đối cách làm đó, tại sao em lại trích dẫn rằng ông ấy ủng hộ?", "warning"),
                            DialogueLine(CharacterId.MINH, "Dạ... em thấy đoạn tóm tắt ghi như thế ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Trích dẫn sai quan điểm của học giả vì lười đọc tài liệu gốc là lỗi nghiêm trọng về phương pháp nghiên cứu.", "warning")
                        ),
                        outcomeBadge = "Sai lệch quan điểm tác giả",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Tin tưởng mù quáng vào bản tóm tắt rút gọn của AI mà không đối chiếu văn bản gốc.",
                        whatHappened = "Bị phát hiện diễn giải sai quan điểm cốt lõi của công trình khoa học uy tín, gây mất điểm trầm trọng.",
                        whyDoesItMatter = "Trong nghiên cứu, việc trích dẫn một tài liệu đồng nghĩa bạn cam kết đã đọc và hiểu nó. Trích dẫn qua trung gian AI mà không kiểm tra là hành vi vô trách nhiệm.",
                        academicCategory = "Xuyên tạc nguồn tài liệu (Misrepresentation of Sources)",
                        howCouldYouHandleItBetter = "Nếu không có thời gian đọc hết 30 trang, hãy đọc phần Abstract, Introduction và Conclusion của bài báo gốc để nắm chắc lập trường của tác giả trước khi nhờ AI hỗ trợ."
                    ),
                    unlockedCardId = "card_plagiarism"
                ),
                Choice(
                    id = "sc_6_c2",
                    letter = "B",
                    title = "Đọc phần Mở đầu và Kết luận của bài báo gốc, dùng AI đối chiếu những điểm chưa rõ",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Nắm bắt chính xác góc nhìn sắc bén của học giả",
                        narrative = "Minh nhận ra tác giả có góc nhìn phản biện rất sâu sắc. Minh dùng AI để làm rõ định nghĩa thuật ngữ khó hiểu, sau đó tự viết phần tổng quan tài liệu.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MINH, "May mà Mia nhắc mình đọc kết luận, không thì đã hiểu ngược 180 độ ý của tác giả rồi!", "thoughtful"),
                            DialogueLine(CharacterId.DR_LINH, "Tổng quan tài liệu rất có chiều sâu và thể hiện khả năng nắm bắt tư tưởng học thuật tốt.", "confident")
                        ),
                        outcomeBadge = "Tiếp cận nguồn cẩn trọng",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Kết hợp đọc có chiến lược nguồn gốc sơ cấp với sự hỗ trợ giải nghĩa từ vựng của AI.",
                        whatHappened = "Nắm đúng tinh thần của bài báo gốc, tránh được lỗi hiểu sai tai hại.",
                        whyDoesItMatter = "Bảo vệ danh dự học thuật của chính mình và tôn trọng quyền tác giả của nhà nghiên cứu gốc.",
                        academicCategory = "Phương pháp nghiên cứu chuẩn mực (Scholarly Rigor)",
                        howCouldYouHandleItBetter = "Rất tốt! Luôn ghi chép lại các trang cụ thể để trích dẫn số trang chính xác trong bài."
                    ),
                    unlockedCardId = "card_ai_assistance"
                )
            ),
            associatedCardId = "card_ai_assistance"
        ),

        // ==================== CHAPTER 3 ====================
        // Scenario 7
        Scenario(
            id = "sc_7",
            chapterId = 3,
            number = 7,
            title = "Tạp chí ma (The Ghost Citation)",
            subtitle = "AI tạo ra một citation khoa học nghe cực kỳ uy tín",
            location = "Thư viện trường - 20:00",
            summary = "Alex cần thêm 3 tài liệu tham khảo quốc tế về 'Tác động của FinTech đến ngân hàng truyền thống'. AI lập tức xuất ra: 'Smith, J. & Nguyen, T. (2022). FinTech Disruption in ASEAN. Journal of Banking Innovation, 45(2), 112-128.'",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Oa, AI tìm được bài báo chuẩn chỉ đúng chủ đề luôn này! Có cả năm xuất bản, tên tạp chí, số tập số trang!", "confident"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Đây là tài liệu được đề xuất phù hợp với các tiêu chí tìm kiếm của bạn.", "confident"),
                DialogueLine(CharacterId.MIA, "Khoan đã Alex, cậu thử tìm tên tạp chí 'Journal of Banking Innovation' trên Scopus hoặc Google Scholar xem có tồn tại không?", "thoughtful")
            ),
            dilemmaPrompt = "Bạn nhận được một trích dẫn hoàn hảo từ AI nhưng chưa từng nghe tên bài báo này. Bạn sẽ làm gì?",
            choices = listOf(
                Choice(
                    id = "sc_7_c1",
                    letter = "A",
                    title = "Đưa ngay trích dẫn này vào danh mục Tài liệu tham khảo vì trông rất chuyên nghiệp",
                    scoreDelta = -18,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị phát hiện bịa đặt tài liệu tham khảo (Fabrication)",
                        narrative = "Dr. Linh thấy bài báo thú vị nên tìm đọc thử. Tuy nhiên cả Google Scholar, ResearchGate và thư viện đại học đều không có bài báo này. Tạp chí nói trên cũng không hề tồn tại.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, tài liệu tham khảo số 7 của em tìm ở cơ sở dữ liệu nào? Thầy đã tra cứu khắp nơi nhưng không thấy.", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ... AI đưa ra cho em trong danh mục gợi ý ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Đây là lỗi bịa đặt tài liệu khoa học (Fabrication). Trong nghiên cứu, đây là một trong những vi phạm đạo đức nghiêm trọng nhất.", "warning")
                        ),
                        outcomeBadge = "Bịa đặt tài liệu (Fabrication)",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Sử dụng trích dẫn do AI tự sinh mà không xác minh tính có thực.",
                        whatHappened = "Bị quy vào lỗi Fabrication (bịa đặt tài liệu/nguồn tin). Mất toàn bộ điểm phần Phương pháp và Tài liệu tham khảo.",
                        whyDoesItMatter = "Mô hình LLM sinh từ dựa trên xác suất chuỗi từ ngữ (n-gram/transformer), nó ghép nối tên tác giả phổ biến với tên tạp chí nghe có vẻ hợp lý. AI KHÔNG PHẢI là cơ sở dữ liệu khoa học.",
                        academicCategory = "Bịa đặt thông tin & Trích dẫn ma (Fabrication / AI Hallucination)",
                        howCouldYouHandleItBetter = "Luôn tra cứu mã định danh số (DOI) hoặc tìm bài báo trên Google Scholar. Nếu không tìm thấy tệp PDF thực tế, TUYỆT ĐỐI không đưa vào danh mục tham khảo."
                    ),
                    unlockedCardId = "card_ai_hallucination"
                ),
                Choice(
                    id = "sc_7_c2",
                    letter = "B",
                    title = "Tra cứu trên Google Scholar / cơ sở dữ liệu trường, phát hiện không có và loại bỏ ngay",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Thoát khỏi cạm bẫy ảo giác AI trong gang tấc",
                        narrative = "Alex tra cứu và phát hiện bài báo hoàn toàn không tồn tại. Alex lập tức chuyển sang cổng thư viện điện tử của trường để tìm các bài báo có thật của tác giả khác.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "May quá Mia ơi! Đúng là không có bài này thật, AI bịa ra một tạp chí nghe như thật!", "anxious"),
                            DialogueLine(CharacterId.MIA, "Đó gọi là Hallucination đấy. Bài học nhớ đời: chỉ trích dẫn những gì mình đã cầm trên tay hoặc tải được file PDF!", "confident")
                        ),
                        outcomeBadge = "Thẩm định nguồn xuất sắc",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Chủ động kiểm chứng chéo (cross-check) thông tin qua các cơ sở dữ liệu học thuật độc lập.",
                        whatHappened = "Ngăn chặn kịp thời một lỗi vi phạm liêm chính học thuật chết người.",
                        whyDoesItMatter = "Thể hiện phẩm chất hoài nghi khoa học lành mạnh (Healthy Skepticism) cần thiết cho mọi nhà nghiên cứu hiện đại.",
                        academicCategory = "Kiểm định nguồn độc lập (Independent Verification)",
                        howCouldYouHandleItBetter = "Sử dụng các công cụ học thuật chuyên dụng kết nối cơ sở dữ liệu thời gian thực như Scopus, Semantic Scholar, hoặc Web of Science."
                    ),
                    unlockedCardId = "card_ai_hallucination"
                )
            ),
            associatedCardId = "card_ai_hallucination"
        ),

        // Scenario 8
        Scenario(
            id = "sc_8",
            chapterId = 3,
            number = 8,
            title = "Danh mục 20 trích dẫn siêu tốc",
            subtitle = "Yêu cầu có tối thiểu 15 nguồn tài liệu học thuật",
            location = "Phòng trọ - 21:00",
            summary = "Để bài tiểu luận đạt điểm cao, giảng viên yêu cầu danh mục tài liệu tham khảo phải có ít nhất 15 nguồn tiếng Anh. Minh bảo AI sinh ra danh mục 20 nguồn cho đủ số lượng.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MINH, "Bài mình viết xong rồi nhưng mới có 5 nguồn, thiếu tận 10 nguồn nữa mới đủ barem điểm của khoa.", "anxious"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Đây là danh mục 15 bài báo khoa học về Kinh tế tuần hoàn định dạng chuẩn APA 7th.", "confident"),
                DialogueLine(CharacterId.MINH, "Nhanh gọn lẹ! Cứ dán vào cuối bài là đủ chỉ tiêu 20 nguồn, chắc chẳng ai bấm vào đọc từng bài đâu!", "neutral")
            ),
            dilemmaPrompt = "Bạn có nên độn danh mục tài liệu tham khảo bằng các nguồn do AI sinh ra mà trong bài không hề trích dẫn thực chất?",
            choices = listOf(
                Choice(
                    id = "sc_8_c1",
                    letter = "A",
                    title = "Dán danh mục nguồn của AI vào cuối bài để đủ chỉ tiêu số lượng",
                    scoreDelta = -15,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị trừ điểm vì trích dẫn rác (Padding References)",
                        narrative = "Dr. Linh kiểm tra đối chiếu giữa các câu trích dẫn trong thân bài (in-text citation) và danh mục cuối bài. Thầy phát hiện 10 nguồn ở cuối bài không hề được nhắc tới trong bất kỳ luận điểm nào.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Minh, tại sao ở cuối bài có bài báo của tác giả Zhang (2020) nhưng trong toàn bộ bài em không hề viện dẫn ý tưởng nào của ông ấy?", "warning"),
                            DialogueLine(CharacterId.MINH, "Dạ... em đưa vào để người đọc tham khảo thêm ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Độn nguồn cho đẹp danh mục là hành vi phi học thuật. Mỗi tài liệu liệt kê phải phục vụ một luận cứ cụ thể.", "warning")
                        ),
                        outcomeBadge = "Độn nguồn tham khảo",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Làm đẹp hình thức danh mục tài liệu mà không có nội dung học thuật tương ứng.",
                        whatHappened = "Bị đánh giá là thiếu trung thực về mặt phương pháp, bị trừ điểm chuyên cần học thuật.",
                        whyDoesItMatter = "Tài liệu tham khảo là minh chứng cho quá trình đọc và nghiên cứu thực tế. Liệt kê tài liệu chưa đọc làm sai lệch bản chất của nghiên cứu.",
                        academicCategory = "Gian lận trích dẫn (Citation Padding / Misconduct)",
                        howCouldYouHandleItBetter = "Thà có 5 nguồn chất lượng được phân tích sâu sắc còn hơn 20 nguồn 'ma' chỉ để làm cảnh. Hãy thẳng thắn trao đổi với giảng viên nếu gặp khó khăn khi tìm tài liệu."
                    ),
                    unlockedCardId = "card_plagiarism"
                ),
                Choice(
                    id = "sc_8_c2",
                    letter = "B",
                    title = "Chỉ giữ lại những nguồn mình đã đọc và trích dẫn thực tế, tìm thêm sách giáo trình nếu cần",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Danh mục tài liệu chuẩn mực và thuyết phục",
                        narrative = "Minh dành thêm 1 tiếng tìm kiếm trên thư viện số, chọn lọc thêm 4 bài nghiên cứu có thật và đọc kỹ trước khi đưa vào bài.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MINH, "Tuy chỉ có 9 nguồn nhưng nguồn nào mình cũng chỉ ra được câu trích dẫn rõ ràng trong bài.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Thầy đánh giá cao việc em trích dẫn chính xác và gắn kết từng nguồn với từng lập luận cụ thể.", "confident")
                        ),
                        outcomeBadge = "Trích dẫn chuẩn mực",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Tuân thủ nguyên tắc: chỉ đưa vào danh mục những tài liệu có đóng góp thực sự vào bài viết.",
                        whatHappened = "Bài viết có độ tin cậy học thuật cao, dễ dàng bảo vệ trước mọi câu hỏi chất vấn.",
                        whyDoesItMatter = "Tôn trọng sự thật và công sức của các nhà khoa học đi trước.",
                        academicCategory = "Chuẩn mực trích dẫn học thuật (Ethical Referencing)",
                        howCouldYouHandleItBetter = "Rất tốt! Sử dụng các phần mềm quản lý trích dẫn như Zotero hoặc Mendeley để đồng bộ in-text citation và bibliography tự động."
                    ),
                    unlockedCardId = "card_ai_assistance"
                )
            ),
            associatedCardId = "card_plagiarism"
        ),

        // Scenario 9
        Scenario(
            id = "sc_9",
            chapterId = 3,
            number = 9,
            title = "Phân tích dữ liệu 'đen mù'",
            subtitle = "Nhờ AI chạy code phân tích thống kê nhưng không kiểm tra lại",
            location = "Phòng máy thực hành - 15:00",
            summary = "Alex có một tập dữ liệu 500 dòng khảo sát khách hàng. Alex tải file CSV lên AI và bảo: 'Chạy hồi quy tuyến tính tìm mối quan hệ giữa giá cả và độ hài lòng'. AI trả về p-value = 0.001 và hệ số R2 = 0.85.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Hay quá, AI tự làm sạch dữ liệu, xử lý giá trị khuyết tật (missing data) và chạy ra kết quả hồi quy cực đẹp!", "confident"),
                DialogueLine(CharacterId.MIA, "Alex ơi, cậu có biết AI đã xử lý các dòng bị thiếu dữ liệu bằng cách nào không? Điền số trung bình hay xóa dòng?", "thoughtful"),
                DialogueLine(CharacterId.ALEX, "Ai quan tâm chi tiết kỹ thuật đó chứ, miễn là p-value < 0.05 có ý nghĩa thống kê là nộp được rồi!", "neutral")
            ),
            dilemmaPrompt = "Sử dụng kết quả phân tích dữ liệu của AI mà không nắm rõ phương pháp xử lý có rủi ro gì?",
            choices = listOf(
                Choice(
                    id = "sc_9_c1",
                    letter = "A",
                    title = "Nộp ngay kết quả phân tích của AI mà không hỏi rõ cách thức xử lý dữ liệu thô",
                    scoreDelta = -14,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Kết quả nghiên cứu bị hủy vì phương pháp sai lệch",
                        narrative = "Dr. Linh yêu cầu nộp file mã nguồn (code) hoặc nhật ký xử lý dữ liệu. Kiểm tra lại mới vỡ lẽ: AI đã tự ý điền giá trị 0 vào các ô dữ liệu trống, khiến kết quả hồi quy bị lệch hoàn toàn.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, các ô khách hàng không trả lời thu nhập lại bị gán bằng 0 triệu đồng? Điều này làm sai lệch toàn bộ mô hình kinh tế lượng của em!", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ em tưởng AI tự động chọn giải pháp tối ưu nhất rồi ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Nhà nghiên cứu phải kiểm soát dữ liệu của mình. Để cỗ máy tự quyết định giả định là vi phạm phương pháp luận nghiêm trọng.", "warning")
                        ),
                        outcomeBadge = "Sai lệch dữ liệu nghiên cứu",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Chấp nhận kết quả phân tích của AI như một chiếc 'hộp đen' (Black Box) thiếu minh bạch.",
                        whatHappened = "Mô hình toán học đưa ra kết luận sai lệch thực tế do sai sót trong khâu tiền xử lý dữ liệu (data preprocessing).",
                        whyDoesItMatter = "Trong khoa học dữ liệu và kinh tế học, 'Rác vào thì Rác ra' (Garbage In, Garbage Out). Người nghiên cứu chịu trách nhiệm giải trình về mọi giả định thống kê.",
                        academicCategory = "Làm sai lệch phương pháp luận (Methodological Falsification)",
                        howCouldYouHandleItBetter = "Yêu cầu AI xuất ra script code chi tiết (Python/R), đọc hiểu từng dòng tiền xử lý và tự tay chạy lại trên máy cá nhân."
                    ),
                    unlockedCardId = "card_fabrication"
                ),
                Choice(
                    id = "sc_9_c2",
                    letter = "B",
                    title = "Yêu cầu AI xuất ra mã code Python, tự kiểm tra từng bước tiền xử lý và chạy lại trên Jupyter Notebook",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Bảo vệ kết quả nghiên cứu vững chắc",
                        narrative = "Alex đọc code, phát hiện AI định điền giá trị 0. Alex yêu cầu sửa lại bằng phương pháp loại bỏ mẫu khuyết (listwise deletion) và lưu lại toàn bộ log phân tích.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "May mà kiểm tra lại, suýt nữa thì toàn bộ kết luận bị bóp méo!", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Phần giải trình quy trình làm sạch dữ liệu của em rất chặt chẽ và chuyên nghiệp.", "confident")
                        ),
                        outcomeBadge = "Nghiên cứu có trách nhiệm",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Giữ quyền làm chủ phương pháp khoa học, bắt buộc AI phải minh bạch mã nguồn.",
                        whatHappened = "Dữ liệu được xử lý chính xác, kết quả nghiên cứu có tính lặp lại (reproducibility) cao.",
                        whyDoesItMatter = "Tính minh bạch và khả năng tái lập là hai trụ cột căn bản của liêm chính khoa học hiện đại.",
                        academicCategory = "Liêm chính khoa học dữ liệu (Data Integrity & Reproducibility)",
                        howCouldYouHandleItBetter = "Đính kèm notebook phân tích dữ liệu vào phần phụ lục của bài nghiên cứu để giảng viên dễ dàng thẩm định."
                    ),
                    unlockedCardId = "card_fabrication"
                )
            ),
            associatedCardId = "card_fabrication"
        ),

        // ==================== CHAPTER 4 ====================
        // Scenario 10
        Scenario(
            id = "sc_10",
            chapterId = 4,
            number = 10,
            title = "Cám dỗ Take-Home Exam",
            subtitle = "Bài thi 120 phút tại nhà với quy chế nghiêm cấm công cụ hỗ trợ ngoài",
            location = "Phòng riêng - 14:00 (Đang trong giờ thi)",
            summary = "Kỳ thi kết thúc học phần diễn ra dưới hình thức Take-home Exam trực tuyến. Đề thi ghi rõ bằng chữ in đậm: 'ĐỀ THI ĐÓNG - SINH VIÊN KHÔNG ĐƯỢC PHÉP SỬ DỤNG AI, INTERNET HOẶC SỰ TRỢ GIÚP TỪ BÊN NGOÀI'. Alex gặp một câu tự luận 4 điểm hóc búa.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Đề thi ghi rõ cấm dùng AI... Nhưng đang thi ở nhà, camera chỉ quay góc mặt, mình mở một tab trình duyệt phụ thì ai biết được?", "anxious"),
                DialogueLine(CharacterId.MINH, "Tôi cá với cậu 80% lớp đang dùng ChatGPT để làm câu 4 điểm này đấy! Cậu không dùng là tự chịu thiệt thòi!", "warning"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Tôi sẵn sàng phân tích đề thi và phác thảo câu trả lời hoàn chỉnh nếu bạn cung cấp văn bản đề.", "neutral")
            ),
            dilemmaPrompt = "Khi quy chế thi ghi rõ KHÔNG ĐƯỢC PHÉP dùng AI, bạn sẽ lựa chọn ra sao?",
            choices = listOf(
                Choice(
                    id = "sc_10_c1",
                    letter = "A",
                    title = "Dán đề thi vào AI để lấy gợi ý câu trả lời và nộp bài thi",
                    scoreDelta = -20,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị đình chỉ thi và lập biên bản kỷ luật",
                        narrative = "Hệ thống giám sát thi cử của trường ghi nhận hành vi chuyển cửa sổ và mẫu câu văn trong bài thi trùng khớp với câu trả lời chuẩn của mô hình AI. Alex bị đình chỉ học phần và nhận điểm 0.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Quy chế thi đã ghi rõ bằng văn bản. Việc sử dụng công cụ ngoài trong giờ thi không còn là ranh giới xám nữa, mà là hành vi vi phạm kỷ luật thi cử.", "warning"),
                            DialogueLine(CharacterId.ALEX, "Em... em chỉ tham khảo một chút vì thấy câu hỏi khó quá ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Biên bản kỷ luật đã được chuyển lên Hội đồng khen thưởng và kỷ luật sinh viên.", "warning")
                        ),
                        outcomeBadge = "Kỷ luật thi cử",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Cố tình vi phạm lệnh cấm rõ ràng của quy chế thi.",
                        whatHappened = "Bị điểm F học phần, ghi nhận vào hồ sơ kỷ luật sinh viên và làm tổn hại nghiêm trọng danh dự cá nhân.",
                        whyDoesItMatter = "Khác với bài tập thông thường (nơi AI có thể hỗ trợ học tập), kỳ thi là thước đo đánh giá cá nhân. Vi phạm quy chế thi là hành vi gian lận học thuật nghiêm trọng nhất.",
                        academicCategory = "Gian lận trong thi cử / Hỗ trợ trái phép (Unauthorized Assistance in Exams)",
                        howCouldYouHandleItBetter = "Quy chế thi là ranh giới đỏ tuyệt đối. Khi đề thi cấm, 0% AI là lựa chọn duy nhất. Hãy vận dụng tối đa kiến thức đã ôn tập để hoàn thành bài thi."
                    ),
                    unlockedCardId = "card_unauthorized_aid"
                ),
                Choice(
                    id = "sc_10_c2",
                    letter = "B",
                    title = "Tuân thủ quy chế, đóng toàn bộ ứng dụng ngoài và tự lực làm bài thi bằng kiến thức của mình",
                    scoreDelta = 15,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Điểm số thực chất và sự thanh thản tuyệt đối",
                        narrative = "Alex tập trung làm bài thi dựa trên những gì đã ôn tập. Dù điểm số có thể không hoàn hảo 10/10, nhưng Alex ngẩng cao đầu tự hào về sự trung thực của mình.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Dù câu 4 điểm mình chỉ làm được khoảng 70% ý, nhưng đó là năng lực thật của mình.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Thầy đánh giá rất cao những sinh viên có bản lĩnh giữ gìn tính trung thực ngay cả khi không có ai giám sát trực tiếp.", "confident")
                        ),
                        outcomeBadge = "Bản lĩnh liêm chính",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Tôn trọng cam kết danh dự và quy định khảo thí của nhà trường.",
                        whatHappened = "Bạn hoàn thành kỳ thi trong sự an tâm, không phải nơm nớp lo sợ bị phát hiện.",
                        whyDoesItMatter = "Liêm chính học thuật là làm điều đúng đắn ngay cả khi không có ai nhìn thấy.",
                        academicCategory = "Liêm chính trong khảo thí (Exam Integrity)",
                        howCouldYouHandleItBetter = "Bạn đã có quyết định vô cùng đúng đắn và dũng cảm trước áp lực đồng trang lứa!"
                    ),
                    unlockedCardId = "card_unauthorized_aid"
                )
            ),
            associatedCardId = "card_unauthorized_aid"
        ),

        // Scenario 11
        Scenario(
            id = "sc_11",
            chapterId = 4,
            number = 11,
            title = "Chia sẻ bài cũ cho bạn thân",
            subtitle = "Bạn thân nhờ gửi bài tập lớn kỳ trước để 'tham khảo'",
            location = "Tin nhắn Zalo - 22:15",
            summary = "Minh nhắn tin cho Mia xin bài tập lớn môn Marketing mà Mia đã đạt điểm A kỳ trước. Minh hứa: 'Chỉ xin để tham khảo cấu trúc thôi, mình sẽ bảo AI xào xáo lại từ ngữ nên đảm bảo không bao giờ bị trùng Turnitin đâu!'.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MINH, "Mia ơi cứu bồ với! Cho mình xin file bài tiểu luận kỳ trước của cậu đi! Đảm bảo chỉ tham khảo thôi!", "anxious"),
                DialogueLine(CharacterId.MIA, "Nhưng trường mình dùng hệ thống lưu trữ toàn bộ bài cũ, nhỡ bị quét trùng lặp thì sao?", "thoughtful"),
                DialogueLine(CharacterId.MINH, "Yên tâm đi, mình có tool AI Paraphrase Pro, nó đảo câu và đổi hết từ vựng rồi, thầy cô không nhận ra bài cũ đâu!", "confident")
            ),
            dilemmaPrompt = "Khi bạn bè nhờ chia sẻ bài cũ với ý định dùng AI để 'xào xáo', bạn xử lý thế nào?",
            choices = listOf(
                Choice(
                    id = "sc_11_c1",
                    letter = "A",
                    title = "Gửi cả file bài làm cũ của mình cho bạn vì nể nang tình bạn",
                    scoreDelta = -14,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Cả hai cùng bị triệu tập vì lỗi đồng lõa gian lận (Collusion)",
                        narrative = "Hệ thống Turnitin phát hiện bài của Minh trùng khớp cấu trúc lập luận với bài trong kho lưu trữ của Mia. Cả Mia và Minh đều bị gửi thư mời lên Hội đồng kỷ luật để giải trình.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Mia, em là người cung cấp bài làm cho Minh sao chép đúng không? Trong quy chế, hành vi tiếp tay cho gian lận (Collusion) bị xử lý tương đương người gian lận.", "warning"),
                            DialogueLine(CharacterId.MIA, "Em chỉ muốn giúp bạn tham khảo thôi ạ... Em không ngờ bạn lại làm như thế...", "anxious"),
                            DialogueLine(CharacterId.MINH, "Mình xin lỗi Mia, mình không nghĩ phần mềm quét được cả bài đã nộp từ năm ngoái...", "anxious")
                        ),
                        outcomeBadge = "Đồng lõa gian lận (Collusion)",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Chia sẻ bài làm hoàn chỉnh của mình khi biết rõ bạn có ý định sao chép.",
                        whatHappened = "Cả người cho mượn và người sao chép đều bị xử lý kỷ luật vì hành vi thông đồng gian lận học thuật.",
                        whyDoesItMatter = "Giúp đỡ bạn bè là tốt, nhưng tiếp tay cho việc gian lận là làm hại bạn và tự hủy hoại uy tín học thuật của chính mình.",
                        academicCategory = "Thông đồng gian lận học thuật (Collusion / Unauthorized Sharing)",
                        howCouldYouHandleItBetter = "Từ chối khéo léo việc gửi file bài làm. Thay vào đó, bạn có thể hẹn bạn ra quán cà phê để chia sẻ về phương pháp tìm kiếm tài liệu hoặc kinh nghiệm học tập."
                    ),
                    unlockedCardId = "card_peer_collaboration"
                ),
                Choice(
                    id = "sc_11_c2",
                    letter = "B",
                    title = "Từ chối gửi bài làm, đề nghị hướng dẫn bạn cách xây dựng dàn ý và tìm tài liệu",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Giúp bạn thực chất và giữ vững tình bạn",
                        narrative = "Mia giải thích rõ quy định lưu trữ của nhà trường và dành 30 phút gọi điện hướng dẫn Minh cách lập đề cương môn học. Minh tự tay hoàn thành bài và cảm ơn Mia.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Mình không thể gửi file bài được vì quy định trường rất nghiêm, nhưng mình có thể chỉ cậu cách mình đã tìm tài liệu ở thư viện.", "confident"),
                            DialogueLine(CharacterId.MINH, "Cảm ơn Mia nhé! Nhờ cậu giải thích đề cương mà mình biết hướng làm rồi, không cần phải xào bài cũ nữa!", "confident")
                        ),
                        outcomeBadge = "Hỗ trợ chuẩn mực",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Thiết lập ranh giới học thuật lành mạnh, chuyển hướng từ giúp đỡ tiêu cực sang hỗ trợ phương pháp.",
                        whatHappened = "Bảo vệ an toàn cho cả hai bạn, đồng thời giúp bạn mình thực sự học được kiến thức môn học.",
                        whyDoesItMatter = "Tình bạn đích thực trong môi trường đại học là cùng nhau tiến bộ và tôn trọng các chuẩn mực chung.",
                        academicCategory = "Hỗ trợ đồng đẳng có trách nhiệm (Peer Support & Mentorship)",
                        howCouldYouHandleItBetter = "Đây là cách hành xử văn minh, thấu tình đạt lý và đáng noi theo nhất."
                    ),
                    unlockedCardId = "card_peer_collaboration"
                )
            ),
            associatedCardId = "card_peer_collaboration"
        ),

        // Scenario 12
        Scenario(
            id = "sc_12",
            chapterId = 4,
            number = 12,
            title = "Bản dịch của người khác",
            subtitle = "Dùng AI dịch bài báo tiếng nước ngoài rồi nộp như bài phân tích tự viết",
            location = "Ký túc xá - 10:00",
            summary = "Đề bài yêu cầu: 'Viết bài phân tích 1500 từ về chiến lược marketing của Apple'. Alex tìm thấy một bài viết rất xuất sắc của một chuyên gia người Pháp, dùng AI dịch sang tiếng Việt trong 10 giây và nộp bản dịch đó mà không dẫn nguồn tác giả Pháp.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Bài viết của tác giả Pháp này phân tích quá hay! Dùng AI dịch sang tiếng Việt mượt mà như người bản xứ viết vậy!", "confident"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Bản dịch tiếng Việt đã hoàn tất. Cú pháp và thuật ngữ kinh doanh đã được tối ưu hóa chuẩn xác.", "neutral"),
                DialogueLine(CharacterId.MIA, "Alex ơi, dịch bài của người khác rồi nộp mà không ghi tên tác giả gốc thì vẫn là đạo văn đấy nhé, dù là ngôn ngữ khác!", "warning")
            ),
            dilemmaPrompt = "Dịch toàn bộ bài viết của tác giả nước ngoài bằng AI rồi nộp làm bài của mình có vi phạm không?",
            choices = listOf(
                Choice(
                    id = "sc_12_c1",
                    letter = "A",
                    title = "Nộp bản dịch AI làm bài của mình, không ghi nguồn vì cho rằng khác ngôn ngữ sẽ không bị phát hiện",
                    scoreDelta = -16,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Bị phát hiện đạo văn xuyên ngôn ngữ (Cross-Language Plagiarism)",
                        narrative = "Các công cụ chống đạo văn hiện đại của trường tích hợp tính năng phát hiện tương đồng xuyên ngôn ngữ (Cross-language similarity). Bài viết bị đối chiếu trực tiếp với bài gốc tiếng Pháp.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, toàn bộ cấu trúc luận điểm, số liệu và ví dụ minh họa của em trùng khớp 100% với bài báo của chuyên gia Jean-Luc đăng trên Le Figaro.", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ... nhưng em tự dùng AI dịch sang tiếng Việt mà thầy...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Dịch thuật là chuyển đổi ngôn ngữ, không tạo ra quyền sở hữu tác phẩm. Lấy công trình của người khác làm của mình bất kể ngôn ngữ nào đều là đạo văn.", "warning")
                        ),
                        outcomeBadge = "Đạo văn xuyên ngôn ngữ",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Ngộ nhận rằng việc chuyển đổi ngôn ngữ bằng AI xóa bỏ quyền tác giả gốc.",
                        whatHappened = "Bị tính là đạo văn nghiêm trọng (Plagiarism), nhận điểm 0 cho toàn bộ bài tập lớn.",
                        whyDoesItMatter = "Quyền sở hữu trí tuệ bảo vệ ý tưởng và cấu trúc sáng tạo, không phụ thuộc vào ngôn ngữ thể hiện.",
                        academicCategory = "Đạo văn xuyên ngôn ngữ (Cross-Language Plagiarism)",
                        howCouldYouHandleItBetter = "Trích dẫn rõ ràng tác giả Pháp theo quy chuẩn: 'Theo phân tích của Jean-Luc (2023)...'. Bạn chỉ nên trích dẫn một đoạn ngắn và tự mình viết phần bình luận, liên hệ với thị trường Việt Nam."
                    ),
                    unlockedCardId = "card_plagiarism"
                ),
                Choice(
                    id = "sc_12_c2",
                    letter = "B",
                    title = "Trích dẫn tác giả Pháp đàng hoàng, dịch một số ý chính và tập trung viết phần so sánh với Việt Nam",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Bài nghiên cứu so sánh quốc tế xuất sắc",
                        narrative = "Alex nêu rõ nguồn gốc bài viết của chuyên gia Pháp, dùng AI hỗ trợ đọc hiểu tài liệu tiếng Pháp, sau đó tự viết 1000 từ phân tích so sánh thị trường.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Mình đã trích dẫn đầy đủ nguồn gốc tiếng Pháp và thêm phần phân tích thực tế tại hệ thống phân phối Việt Nam.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Rất tốt! Em đã sử dụng công cụ dịch thuật một cách thông minh để tiếp cận tri thức quốc tế và có sự liên hệ thực tiễn sắc sảo.", "confident")
                        ),
                        outcomeBadge = "Nghiên cứu so sánh mẫu mực",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Sử dụng AI như cầu nối ngôn ngữ học tập, tôn trọng quyền tác giả và bổ sung giá trị gia tăng cá nhân.",
                        whatHappened = "Bài viết đạt điểm cao nhờ tính quốc tế, sự trung thực học thuật và khả năng tư duy so sánh.",
                        whyDoesItMatter = "Đây là cách sử dụng AI nâng cao năng lực đọc hiểu tài liệu nước ngoài mà mọi trường đại học khuyến khích.",
                        academicCategory = "Tiếp cận tài liệu quốc tế có trách nhiệm (Cross-Lingual Research)",
                        howCouldYouHandleItBetter = "Rất chuẩn xác! Bạn đã biến một bài dịch thô thành một nghiên cứu so sánh có giá trị học thuật cao."
                    ),
                    unlockedCardId = "card_plagiarism"
                )
            ),
            associatedCardId = "card_plagiarism"
        ),

        // ==================== CHAPTER 5 ====================
        // Scenario 13
        Scenario(
            id = "sc_13",
            chapterId = 5,
            number = 13,
            title = "Người đồng đội vô hình",
            subtitle = "Làm bài nhóm nhưng một thành viên bí mật để AI làm 80% phần việc",
            location = "Phòng họp nhóm thư viện - 17:00",
            summary = "Nhóm 4 người có bài thuyết trình quan trọng. Minh được phân công làm phần 'Phân tích tài chính và đối thủ cạnh tranh'. Đến ngày tổng duyệt, Minh nộp một bản slide dài 20 trang do AI sinh ra hoàn toàn mà không nói gì với nhóm.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MINH, "Mình làm xong phần phân tích rồi nhé, các bạn chỉ việc ghép vào bài thuyết trình thôi!", "confident"),
                DialogueLine(CharacterId.MIA, "Minh ơi, các số liệu thị phần này ở đâu ra? Sao có nhiều công ty lạ hoắc ở thị trường Mỹ chứ không phải Việt Nam?", "thoughtful"),
                DialogueLine(CharacterId.MINH, "À... mình dùng AI tạo tự động cho nhanh, thấy nó vẽ biểu đồ đẹp mắt nên cứ thế lấy thôi...", "neutral")
            ),
            dilemmaPrompt = "Khi phát hiện thành viên nhóm dùng AI làm thay phần việc mà không thông báo, nhóm nên làm gì?",
            choices = listOf(
                Choice(
                    id = "sc_13_c1",
                    letter = "A",
                    title = "Nhắm mắt cho qua, ghép vào bài nộp chung để kịp hạn nộp bài",
                    scoreDelta = -14,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Cả nhóm cùng bị đánh rớt trong buổi vấn đáp",
                        narrative = "Dr. Linh chất vấn bất kỳ một thành viên nào trong nhóm về số liệu của phần Minh làm. Không ai trong nhóm trả lời được vì số liệu là do AI tự sinh vô căn cứ. Cả nhóm cùng bị hạ bậc điểm.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Trong bài tập nhóm, điểm số và trách nhiệm là liên đới. Các em nộp một sản phẩm mà không kiểm tra nội dung của nhau là thiếu tôn trọng hội đồng.", "warning"),
                            DialogueLine(CharacterId.MIA, "Tụi em xin lỗi thầy, tụi em đã chủ quan không rà soát kỹ phần của bạn...", "anxious"),
                            DialogueLine(CharacterId.MINH, "Mình thật sự xin lỗi cả nhóm, vì sự cẩu thả của mình mà ảnh hưởng đến tất cả mọi người...", "anxious")
                        ),
                        outcomeBadge = "Đồng trách nhiệm nhóm",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Thờ ơ trước hành vi thiếu trách nhiệm của đồng đội, chấp nhận rủi ro tập thể.",
                        whatHappened = "Mất điểm uy tín nhóm, tạo ra sự nghi kỵ và đổ vỡ niềm tin giữa các thành viên.",
                        whyDoesItMatter = "Nguyên tắc cốt lõi của làm việc nhóm (Teamwork) là tính đồng trách nhiệm (Mutual Accountability). Một mắt xích gian lận làm ô nhiễm toàn bộ bài nghiên cứu.",
                        academicCategory = "Trách nhiệm hợp tác học thuật (Collaborative Integrity)",
                        howCouldYouHandleItBetter = "Yêu cầu Minh làm lại hoặc cả nhóm cùng ngồi lại 1 tiếng để hỗ trợ Minh thay thế các số liệu ảo bằng số liệu thực tế trước khi nộp."
                    ),
                    unlockedCardId = "card_peer_collaboration"
                ),
                Choice(
                    id = "sc_13_c2",
                    letter = "B",
                    title = "Họp nhóm thẳng thắn, yêu cầu Minh kiểm chứng số liệu hoặc phân công lại phần việc",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Bảo vệ chất lượng bài nhóm và tinh thần đồng đội",
                        narrative = "Cả nhóm ngồi lại cùng Minh, chỉ ra những điểm bất hợp lý của số liệu AI. Minh nhận ra sai lầm và cùng cả nhóm tra cứu báo cáo tài chính thực tế.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Nhóm mình cần trung thực với nhau. Nếu Minh bận, tụi mình có thể phụ, nhưng tuyệt đối không nộp số liệu AI chưa kiểm chứng.", "confident"),
                            DialogueLine(CharacterId.MINH, "Cảm ơn các bạn đã thẳng thắn góp ý. Mình sẽ ngồi làm lại ngay đêm nay bằng số liệu thật!", "confident")
                        ),
                        outcomeBadge = "Hợp tác minh bạch",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Thể hiện kỹ năng lãnh đạo học thuật và văn hóa giao tiếp cởi mở trong làm việc nhóm.",
                        whatHappened = "Bài thuyết trình đạt điểm A nhờ số liệu thực tế vững chắc và sự đồng đều giữa các thành viên.",
                        whyDoesItMatter = "Học cách xử lý bất đồng và bảo vệ liêm chính nhóm là bài học kỹ năng mềm quan trọng bậc nhất của đời sinh viên.",
                        academicCategory = "Văn hóa trách nhiệm nhóm (Group Accountability)",
                        howCouldYouHandleItBetter = "Rất xuất sắc! Thiết lập quy ước sử dụng AI rõ ràng ngay từ buổi họp nhóm đầu tiên."
                    ),
                    unlockedCardId = "card_peer_collaboration"
                )
            ),
            associatedCardId = "card_peer_collaboration"
        ),

        // Scenario 14
        Scenario(
            id = "sc_14",
            chapterId = 5,
            number = 14,
            title = "Ranh giới sửa ngữ pháp (Grammar Polish)",
            subtitle = "Dùng AI sửa ngữ pháp: Khi nào là hỗ trợ ngôn ngữ, khi nào là viết lại nội dung?",
            location = "Bàn làm việc - 20:30",
            summary = "Alex viết một bài luận tiếng Anh. Alex dùng câu lệnh: 'Sửa lỗi ngữ pháp, chính tả cho đoạn văn này'. Nhưng công cụ AI không chỉ sửa lỗi dấu phẩy mà còn tự động viết lại toàn bộ câu văn, nâng cấp từ vựng và chèn thêm các liên từ cao cấp mà Alex chưa từng học.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.ALEX, "Mình chỉ bảo nó sửa ngữ pháp thôi mà nó viết lại thành một đoạn văn nghe như giáo sư Oxford viết vậy!", "anxious"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Tôi đã tối ưu hóa giọng điệu học thuật, cải thiện tính mạch lạc và nâng cao vốn từ vựng chuyên ngành cho bạn.", "confident"),
                DialogueLine(CharacterId.MIA, "Alex cẩn thận nhé, ranh giới giữa 'Proofreading' (sửa lỗi) và 'Substantive Editing' (sửa đổi nội dung cốt lõi) rất mong manh đấy.", "thoughtful")
            ),
            dilemmaPrompt = "Khi AI tự ý viết lại bài luận của bạn ở mức độ quá sâu, bạn nên xử lý ra sao?",
            choices = listOf(
                Choice(
                    id = "sc_14_c1",
                    letter = "A",
                    title = "Chấp nhận toàn bộ bản viết lại hoa mỹ của AI và nộp luôn",
                    scoreDelta = -10,
                    decisionType = DecisionType.RISKY,
                    consequence = Consequence(
                        title = "Mất đi giọng văn nguyên bản và bị nghi ngờ năng lực",
                        narrative = "Dr. Linh nhận ra bài luận tiếng Anh của Alex vượt xa trình độ giao tiếp thực tế trên lớp. Thầy yêu cầu Alex dịch ngược lại các cấu trúc phức tạp này sang tiếng Việt, Alex bối rối không làm được.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.DR_LINH, "Alex, cấu trúc 'Notwithstanding the aforementioned dichotomy' này em học ở đâu?", "warning"),
                            DialogueLine(CharacterId.ALEX, "Dạ... em dùng công cụ hỗ trợ ngữ pháp tự động đổi câu của em ạ...", "anxious"),
                            DialogueLine(CharacterId.DR_LINH, "Môn học này nhằm rèn luyện năng lực diễn đạt của em. Khi để AI viết lại hoàn toàn, em đã đánh mất tiếng nói cá nhân (Authorial Voice).", "warning")
                        ),
                        outcomeBadge = "Mất giọng văn cá nhân",
                        isPositive = false
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Để công cụ AI can thiệp quá sâu vào văn phong và cấu trúc lập luận cá nhân.",
                        whatHappened = "Bài viết bị 'AI hóa', mất tính tự nhiên và gây nghi vấn về năng lực thực tế của người học.",
                        whyDoesItMatter = "Sửa lỗi chính tả/ngữ pháp (Proofreading) là hợp lệ, nhưng để AI thay đổi cấu trúc câu và từ vựng vượt quá khả năng hiểu biết của mình là ranh giới xám có nguy cơ vi phạm.",
                        academicCategory = "Can thiệp văn bản quá mức (Over-editing / Voice Displacement)",
                        howCouldYouHandleItBetter = "Sử dụng câu lệnh chặt chẽ: 'Chỉ chỉ ra các lỗi sai chính tả và ngữ pháp cụ thể, giải thích lý do sai, KHÔNG viết lại toàn bộ câu văn'."
                    ),
                    unlockedCardId = "card_ai_assistance"
                ),
                Choice(
                    id = "sc_14_c2",
                    letter = "B",
                    title = "Chỉ chấp nhận các sửa lỗi chính tả đơn thuần, giữ nguyên cấu trúc câu và từ vựng tự nhiên của mình",
                    scoreDelta = 12,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Văn phong tiến bộ thực chất và tự nhiên",
                        narrative = "Alex đối chiếu từng lỗi ngữ pháp, học được cách dùng mạo từ 'a/an/the' đúng chỗ và giữ nguyên lối hành văn chân thật của mình.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.ALEX, "Mình hiểu vì sao câu này bị chia sai thì rồi. Giữ nguyên câu của mình đọc tự tin hơn hẳn!", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Bài viết có tiến bộ rõ rệt về độ chính xác ngữ pháp, câu cú mộc mạc nhưng diễn đạt mạch lạc.", "confident")
                        ),
                        outcomeBadge = "Học hỏi ngôn ngữ hiệu quả",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Kiểm soát quá trình chỉnh sửa, biến AI thành công cụ học tập ngôn ngữ thay vì công cụ tô vẽ giả tạo.",
                        whatHappened = "Trình độ tiếng Anh thực tế được cải thiện, bài viết giữ được tính chân thật.",
                        whyDoesItMatter = "Giảng viên đánh giá cao sự tiến bộ thực chất hơn là một lớp vỏ bọc hoàn hảo nhưng vay mượn.",
                        academicCategory = "Sử dụng công cụ ngôn ngữ có trách nhiệm (Responsible Language Tool Usage)",
                        howCouldYouHandleItBetter = "Đây là phương pháp sử dụng công cụ kiểm tra ngôn ngữ hoàn hảo nhất."
                    ),
                    unlockedCardId = "card_ai_assistance"
                )
            ),
            associatedCardId = "card_ai_assistance"
        ),

        // Scenario 15
        Scenario(
            id = "sc_15",
            chapterId = 5,
            number = 15,
            title = "Người gia sư bị cấm",
            subtitle = "Giảng viên cấm AI hoàn toàn, nhưng AI giải thích khái niệm quá dễ hiểu",
            location = "Góc tự học - 21:00",
            summary = "Đề cương môn Triết học của thầy Nam ghi rõ: 'Nghiêm cấm tuyệt đối việc sử dụng AI dưới mọi hình thức trong môn học này'. Mia đang vô cùng chật vật với khái niệm 'Biện chứng duy vật'. Mia muốn hỏi AI giải thích bằng ngôn ngữ đời thường để hiểu bài.",
            initialDialogues = listOf(
                DialogueLine(CharacterId.MIA, "Thầy Nam cấm AI 100% trong đề cương... Nhưng sách giáo trình viết trừu tượng quá, mình đọc 5 lần vẫn không hiểu 'Tha hóa lao động' là gì!", "anxious"),
                DialogueLine(CharacterId.AI_ASSISTANT, "Tôi có thể giải thích khái niệm 'Tha hóa lao động' của Karl Marx bằng ví dụ về một người thợ làm bánh một cách cực kỳ trực quan!", "confident"),
                DialogueLine(CharacterId.MINH, "Cấm là cấm nộp bài AI viết thôi! Chứ cậu dùng nó để hiểu bài rồi tự lấy ví dụ của mình thì có gì sai?", "thoughtful")
            ),
            dilemmaPrompt = "Khi môn học cấm AI tuyệt đối, việc dùng AI làm công cụ tự học/gia sư cá nhân nên được nhìn nhận như thế nào?",
            choices = listOf(
                Choice(
                    id = "sc_15_c1",
                    letter = "A",
                    title = "Dùng AI để hiểu khái niệm qua ví dụ đời thường, sau đó đọc lại giáo trình và tự viết bài bằng ngôn ngữ của mình",
                    scoreDelta = 10,
                    decisionType = DecisionType.GREY_ZONE,
                    consequence = Consequence(
                        title = "Vượt qua rào cản nhận thức nhưng cần thận trọng về quy chế",
                        narrative = "Nhờ ví dụ người thợ làm bánh, Mia đã thấu hiểu bản chất triết học. Mia tự mình viết bài tiểu luận phân tích và không trích dẫn hay sao chép bất kỳ câu từ nào của AI. Tuy nhiên, Mia vẫn băn khoăn về quy định cấm tuyệt đối của thầy.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Mình đã hiểu bản chất khái niệm và tự viết 100%. Nhưng quy chế cấm tuyệt đối khiến mình cảm thấy có chút băn khoăn...", "thoughtful"),
                            DialogueLine(CharacterId.DR_LINH, "Đây là vùng xám điển hình. Nhiều giảng viên cấm AI vì lo ngại sinh viên nộp bài sao chép, nhưng về bản chất tự học, nếu em không nộp sản phẩm của AI thì mục tiêu giáo dục vẫn đạt được. Tuy nhiên, hãy luôn trao đổi thẳng thắn với thầy.", "confident")
                        ),
                        outcomeBadge = "Vùng xám học tập (Grey Zone)",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Sử dụng AI như phương tiện gia sư hỗ trợ nhận thức cá nhân, không đưa sản phẩm AI vào bài làm cuối cùng.",
                        whatHappened = "Bạn tiếp thu được kiến thức khó mà không vi phạm tính nguyên bản của bài nộp, nhưng đi sát ranh giới quy chế môn học.",
                        whyDoesItMatter = "Sự xung đột giữa quy chế cứng nhắc và nhu cầu tự học cá nhân là bài toán lớn của giáo dục hiện đại. Tự học không phải là gian lận, nhưng sự tuân thủ quy định lớp học vẫn cần được tôn trọng.",
                        academicCategory = "Vùng xám quy chế học tập (Policy Nuance & Cognitive Scaffolding)",
                        howCouldYouHandleItBetter = "Hãy chủ động hỏi giảng viên trong giờ giải lao: 'Thưa thầy, em gặp khó khăn khi đọc giáo trình, em có thể dùng AI như một cuốn từ điển giải thích thuật ngữ để em tự học được không?' Sự minh bạch luôn giải tỏa mọi hiểu lầm."
                    ),
                    unlockedCardId = "card_disclosure"
                ),
                Choice(
                    id = "sc_15_c2",
                    letter = "B",
                    title = "Tuyệt đối không chạm vào AI, đến gặp trực tiếp thầy Nam trong giờ Office Hours để nhờ thầy giảng lại",
                    scoreDelta = 14,
                    decisionType = DecisionType.RESPONSIBLE,
                    consequence = Consequence(
                        title = "Kết nối trực tiếp đầy cảm hứng với người thầy",
                        narrative = "Thầy Nam rất vui khi thấy một sinh viên chủ động đến văn phòng hỏi sâu về triết học. Thầy kiên nhẫn giảng giải và gợi ý những tài liệu phụ trợ tuyệt vời.",
                        followUpDialogues = listOf(
                            DialogueLine(CharacterId.MIA, "Thầy giảng trực tiếp nghe cuốn hút hơn nhiều! Thầy còn chỉ cho em cách liên hệ với các hiện tượng xã hội ngày nay nữa.", "confident"),
                            DialogueLine(CharacterId.DR_LINH, "Tương tác giữa thầy và trò là giá trị mà không một mô hình ngôn ngữ lớn nào có thể thay thế hoàn toàn được.", "confident")
                        ),
                        outcomeBadge = "Kết nối học thuật sâu sắc",
                        isPositive = true
                    ),
                    ethicsAnalysis = EthicsAnalysis(
                        yourDecision = "Tận dụng tối đa nguồn lực con người và giờ hỗ trợ sinh viên (Office Hours) của nhà trường.",
                        whatHappened = "Bạn vừa hiểu sâu bài học, vừa tuân thủ 100% quy định khắt khe của môn học và xây dựng mối quan hệ học thuật tốt đẹp với thầy giáo.",
                        whyDoesItMatter = "Giảng viên đại học luôn sẵn lòng hỗ trợ sinh viên ham học. Đừng để AI trở thành bức tường ngăn cách bạn với các chuyên gia thực thụ.",
                        academicCategory = "Giao tiếp học thuật & Tôn trọng quy chế (Scholarly Mentorship)",
                        howCouldYouHandleItBetter = "Đây là lựa chọn mẫu mực, tôn trọng tuyệt đối văn hóa giảng đường và mang lại giá trị học tập bền vững."
                    ),
                    unlockedCardId = "card_disclosure"
                )
            ),
            associatedCardId = "card_disclosure"
        )
    )

    fun getScenario(id: String): Scenario? = SCENARIOS.find { it.id == id }

    fun getScenariosByChapter(chapterId: Int): List<Scenario> =
        SCENARIOS.filter { it.chapterId == chapterId }
}
