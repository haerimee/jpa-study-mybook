import { useCallback, useContext, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";

import { emotionList } from "../util/emotion";

import EmotionItem from "./EmotionItem";
import MyButton from "./MyButton";
import MyHeader from "./MyHeader";
import {call} from "../service/ApiService";


const ReviewEditor = ({ isEdit, originData, bookInfo }) => {
    const contentRef = useRef();
    const [content, setContent] = useState("");
    const [emotion, setEmotion] = useState(3);

    const handleClickEmote = useCallback((emotion) => {
        setEmotion(emotion);
    }, []);
    const navigate = useNavigate();

    const handleSubmit = () => {
        if (content.length < 1) {
            contentRef.current.focus();
            return;
        }

        if (window.confirm(isEdit ? "기록을 수정하시겠습니까?" : "기록을 작성 완료하시겠습니까?")) {
            if (!isEdit) {
                let param = {memberId: 1, content: content, score: emotion, book: bookInfo}
                call("/api/review","POST", param)
                    .then((res) => {
                        alert('작성이 완료 되었습니다.');
                        navigate('/review', { replace: true });
                    });
            } else {
                let param = {content: content, score: emotion}
                call(`/api/review/${originData.id}`,"PUT", param)
                    .then((res) => {
                        alert('수정이 완료 되었습니다.');
                        navigate('/review', { replace: true });
                    });
            }
        }
    }

    const handleRemove = () => {
        if (window.confirm("정말 삭제하시겠습니까?")) {
            call(`/api/review/${originData.id}`,"DELETE", {})
                .then((res) => {
                    alert('삭제 완료 되었습니다.');
                    navigate('/review', { replace: true });
                });
        }
    }

    useEffect(() => {
        if (isEdit) {
            setEmotion(originData.score);
            setContent(originData.content);
        }
    }, [isEdit, originData])


    return (
        <div className="ReviewEditor">
            <MyHeader
                headText={isEdit ? "기록 수정하기" : "기록 작성하기"}
                leftChild={<MyButton text={"< 뒤로가기"} onClick={() => navigate(-1)} />}
                rightChild={
                    isEdit && (
                        <MyButton text={"삭제하기"} type={"negative"} onClick={handleRemove} />
                    )
                }
            />
            <div>
                <section>
                    <h4>평가하기</h4>
                    <div className="input_box emotion_list_wrapper">
                        {emotionList.map((it) => (
                            <EmotionItem
                                key={it.emotion_id}
                                {...it}
                                onClick={handleClickEmote}
                                isSelected={it.emotion_id === emotion}
                            />
                        ))}
                    </div>
                </section>
                <section>
                    <h4>기록</h4>
                    <div className="input_box_text_wrapper">
                        <textarea
                            ref={contentRef}
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        />
                    </div>
                </section>
                <section>
                    <div className="control_box">
                        <MyButton text={"취소하기"} onClick={() => navigate(-1)} />
                        <MyButton text={"작성완료"} type={"positive"} onClick={handleSubmit} />
                    </div>
                </section>
            </div>
        </div>
    )
}

export default ReviewEditor;