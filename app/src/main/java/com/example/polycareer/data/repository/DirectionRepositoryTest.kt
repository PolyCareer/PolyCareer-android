package com.example.polycareer.data.repository

import com.example.polycareer.domain.model.DirectionInfo
import com.example.polycareer.domain.repository.DirectionRepository

class DirectionRepositoryTest : DirectionRepository {
    private val directionsList = listOf(
        DirectionInfo(
            1,
            "Фундаментальная информатика и информационные технологии",
            "Основной целью программы является подготовка специалистов, обладающих фундаментальными знаниями и практическими навыками в области теоретической и прикладной математики, в области построения информационных систем и сетей, программной инженерии, математического моделирования, глубоким знанием основ информатики. Отличительной чертой подготовки является именно фундаментальность, позволяющая в будущем выпускникам быстро осваивать постоянно изменяющиеся конкретные технологии разработки информационных систем.",
            "https://icst.spbstu.ru/edu/02.03.02/"
        ),
        DirectionInfo(
            2,
            "Математическое обеспечение и администрирование информационных систем",
            "Прикладная математика, проектирование и администрирование баз данных, теория и технология программирования высокоуровневых информационных систем – сильный учебный план позволяет с уверенностью смотреть в будущее нашим студентам. Междисциплинарные связи и сквозные проекты в рамках практик и стажировок помогают студентам решать комплексные задачи в сфере автоматизации и информатизации, а также анализа данных.",
            "https://icst.spbstu.ru/edu/02.03.03/"
        ),
        DirectionInfo(
            3,
            "Информатика и вычислительная техника",
            "Основной особенностью программы является сбалансированная подготовка, включающая обучение как проектированию и разработке аппаратных устройств и систем, так и программного обеспечения. Таким образом, навыки, получаемые осваиваемые выпускником, содержат как аппаратные, так и программные составляющие. Комплексные специалисты такого рода более конкурентноспособны на рынке проектирования программных и аппаратных систем.",
            "https://icst.spbstu.ru/edu/09.03.01/"
        ),
        DirectionInfo(
            4,
            "Информационные системы и технологии",
            "В процессе освоения образовательной программы выпускники получают компетенций в области, исследования, разработки, внедрения и сопровождение информационно управляющих систем и технологий, информационного обслуживания технических систем различного назначения, разработки теоретических и практических средств для описания, анализа, синтеза и оптимизации информационных процессов в таких системах.",
            "https://icst.spbstu.ru/edu/09.03.02/"
        ),
        DirectionInfo(
            5,
            "Прикладная информатика",
            "Учебный план и программы практик данной образовательной программы формируют ключевые компетенции в области технологий web-разработки и разработки распределенных систем, интеллектуальных технологий и прикладной математики в области ИТ. Междисциплинарные связи позволяют студентам работать над комплексными проектами, реализуя все этапы их жизненного цикла.",
            "https://icst.spbstu.ru/edu/09.03.03/"
        ),
        DirectionInfo(
            6,
            "Программная инженерия",
            "Программа ориентирована на подготовку специалистов в области промышленной разработки и сопровождения программного обеспечения, владеющих знаниями основ алгоритмов и структур данных и способных применять современные инструменты, технологии и методы разработки.",
            "https://icst.spbstu.ru/edu/09.03.04/"
        ),
        DirectionInfo(
            7,
            "Управление качеством",
            "Целью программы является подготовка специалистов по управлению качеством - специалистов по управлению процессом создания конкурентоспособных товаров и услуг с применением информационных  технологий, имеющих как общую фундаментальную, так и специальную подготовку. Специалистов, умеющих организовать производство и управление качеством выпускаемой продукции, владеющих экономическими знаниями, средствами вычислительной техники, и способных организовать систему менеджмента качества на предприяти.",
            "https://icst.spbstu.ru/edu/27.03.02/"
        ),
        DirectionInfo(
            8,
            "Системный анализ и управление",
            "Основными объектами профессиональной деятельности выпускников образовательной программы являются системно-аналитические комплексы, информационно-управляющие и другие виды систем, их компоненты и инструментальные средства, требующие исследования, анализа синтеза, разработки, производства, эксплуатации и утилизации, на основе принципов, методов и средств системного анализа, управления, моделирования, математического, программного и аппаратного обеспечения.",
            "https://icst.spbstu.ru/edu/27.03.03/"
        ),
        DirectionInfo(
            9,
            "Управление в технических системах",
            "Мы готовим студентов для решения задач в области автоматизации, обработки данных и информационного обеспечения интеллектуальных систем управления -- как для крупных промышленных производств, так и для создания систем по типу \"Умный дом\". Наша задача - дать студентам знания о фундаментальных основах теории систем искусственного интеллекта и распределенных интеллектуальных систем.",
            "https://icst.spbstu.ru/edu/27.03.04/"
        ),
        DirectionInfo(
            10,
            "Инноватика",
            "Целью программы является подготовка специалистов по управлению инновациями - по управлению процессом создания конкурентоспособных товаров и услуг на базе научно-технических достижений. В рамках данного направления обеспечивается подготовка выпускников для работы в отделах развития предприятий различных отраслей промышленности и в инновационных предприятиях малого бизнеса:",
            "https://icst.spbstu.ru/edu/27.03.05/"
        )
    )

    override suspend fun getDirection(id: Long): DirectionInfo {
        return directionsList.first { it.id == id }
    }
}