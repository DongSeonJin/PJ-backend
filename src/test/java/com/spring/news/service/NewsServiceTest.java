package com.spring.news.service;

import com.spring.news.entity.News;
import com.spring.news.exception.NewsIdNotFoundException;
import com.spring.news.repository.NewsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class  NewsServiceTest {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

//    @Test
//    @Transactional
//    public void getAllNewsTest(){
//        // given: 테스트용으로 더미데이터 10개 DB에 적재
//        // when: 전체 데이터 가져오기
//        List<News> allNewsList = newsService.getAllNews();
//        //then: 길이가 10일 것이다
//        assertThat(allNewsList.size()).isEqualTo(10);
//    }

    //페이징 처리 방식으로 수정한 getAllNewsTest()
    @Test
    public void getAllNewsTest() {
        // given: 더미 데이터를 DB에 적재함, fixture 세팅
        int pageNum = 1;

        // when: getAllNews 호출
        Page<News> resultPage = newsService.getAllNews(pageNum);

        // then: 결과 페이지는 10개의 아이템을 가져와야 함
        assertThat(resultPage.getContent()).hasSize(10);
    }

    @Test
    public void getNewsByIdTest() {
        // given: 테스트용 데이터베이스에 존재하는 뉴스의 ID fixture 세팅
        long newsId = 1L;

        // when: 뉴스 ID로 조회
        News news = newsService.getNewsById(newsId);

        // then: 해당 뉴스가 예상한 값과 일치하는지 검증
        assertThat(news.getNewsId()).isEqualTo(newsId);
        assertThat(news.getNewsUrl()).isEqualTo("https://google.com");
    }

    @Test
    @Transactional
    public void testDeleteNewsById() {
        // given : fixture 세팅
        long newsId = 1L;

        // when: 삭제 로직 실행
        newsService.deleteNewsById(newsId);

        // then: 삭제된 id로 조회 시 NewsIdNotFoundException 예외 발생
        assertThrows(NewsIdNotFoundException.class, () -> newsService.getNewsById(newsId));
    }



    @Test
    public void searchNewsByKeywordTest() {
        // given: 테스트용 데이터베이스에 존재하는 뉴스의 키워드, 페이지번호 fixture 설정
        String keyword = "Breaking";
        int pageNum = 1;

        // when: searchNewsByKeyword 호출
        Page<News> searchResults = newsService.searchNewsByKeyword(keyword, pageNum);

        // then: 검색 결과는 1개의 뉴스를 가져와야 함
        assertThat(searchResults.getTotalElements()).isEqualTo(1);
    }


}

//    /*Dummy Data input query (TEST DB ONLY)*/
//    INSERT INTO news (news_id, title, content, image_url, news_url, created_at)
//    VALUES
//            (1, 'Breaking News: Exciting Discoveries on Mars', 'Scientists have made groundbreaking discoveries...', 'https://image-url.com/1', 'https://google.com', '2023-08-10 10:30:00'),
//            (2, 'New Study Reveals Surprising Benefits of Chocolate', 'A recent study suggests that chocolate...', 'https://image-url.com/2', 'https://news-url.com/2', '2023-08-09 15:45:00'),
//            (3, 'Tech Giant Unveils Revolutionary AI Product', 'The leading tech company introduced a cutting-edge AI...', 'https://image-url.com/3', 'https://news-url.com/3', '2023-08-08 09:20:00'),
//            (4, 'Global Conference Addresses Climate Change Challenges', 'World leaders gathered to discuss strategies...', 'https://image-url.com/4', 'https://news-url.com/4', '2023-08-07 14:10:00'),
//            (5, 'New Art Exhibition Celebrates Diversity', 'Art enthusiasts are in for a treat as...', 'https://image-url.com/5', 'https://news-url.com/5', '2023-08-06 11:55:00'),
//            (6, 'Space Tourism Industry Continues to Expand', 'More and more people are venturing into space...', 'https://image-url.com/6', 'https://news-url.com/6', '2023-08-05 08:30:00'),
//            (7, 'Health and Wellness Trends for the Coming Year', 'Experts predict the top health and wellness trends...', 'https://image-url.com/7', 'https://news-url.com/7', '2023-08-04 16:25:00'),
//            (8, 'New Music Album Tops Charts Worldwide', 'The latest music release has quickly climbed...', 'https://image-url.com/8', 'https://news-url.com/8', '2023-08-03 13:15:00'),
//            (9, 'Innovative Startup Raises Millions in Funding', 'A startup company has secured a substantial...', 'https://image-url.com/9', 'https://news-url.com/9', '2023-08-02 12:00:00'),
//            (10, 'Cultural Festival Brings Communities Together', 'Residents from various backgrounds came together...', 'https://image-url.com/10', 'https://news-url.com/10', '2023-08-01 07:40:00');
